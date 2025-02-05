/*
 * Copyright 2022 Ren Binden
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rpkit.chat.bukkit.chatchannel.format.part

import com.rpkit.characters.bukkit.character.RPKCharacterService
import com.rpkit.chat.bukkit.RPKChatBukkit
import com.rpkit.chat.bukkit.chatchannel.format.click.ClickAction
import com.rpkit.chat.bukkit.chatchannel.format.hover.HoverAction
import com.rpkit.chat.bukkit.context.DirectedPreFormatMessageContext
import com.rpkit.chat.bukkit.database.table.RPKChatNameColorTable
import com.rpkit.core.service.Services
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import java.util.concurrent.CompletableFuture.supplyAsync
import java.util.logging.Level

@SerializableAs("SenderCharacterNamePart")
class SenderCharacterNamePart(
    private val plugin: RPKChatBukkit,
    font: String? = null,
    color: String? = null,
    isBold: Boolean? = null,
    isItalic: Boolean? = null,
    isUnderlined: Boolean? = null,
    isStrikethrough: Boolean? = null,
    isObfuscated: Boolean? = null,
    insertion: String? = null,
    hover: HoverAction? = null,
    click: ClickAction? = null
) : GenericTextPart(
    plugin,
    font,
    color,
    isBold,
    isItalic,
    isUnderlined,
    isStrikethrough,
    isObfuscated,
    insertion,
    hover,
    click
), ConfigurationSerializable {

    override fun getText(context: DirectedPreFormatMessageContext) = supplyAsync {
        val characterService = Services[RPKCharacterService::class.java] ?: return@supplyAsync ""
        val minecraftProfile = context.senderMinecraftProfile
        if (minecraftProfile != null) {
            val character = characterService.getActiveCharacter(minecraftProfile).join() ?: return@supplyAsync ""
            return@supplyAsync character.name
        } else {
            return@supplyAsync ""
        }
    }.exceptionally { exception ->
        plugin.logger.log(Level.SEVERE, "Failed to get sender character name", exception)
        throw exception
    }

    override fun serialize() = mutableMapOf(
        "font" to font,
        "color" to color,
        "bold" to isBold,
        "italic" to isItalic,
        "underlined" to isUnderlined,
        "strikethrough" to isStrikethrough,
        "obfuscated" to isObfuscated,
        "insertion" to insertion,
        "hover" to hover,
        "click" to click
    )

    companion object {
        @JvmStatic
        fun deserialize(serialized: Map<String, Any>) = SenderCharacterNamePart(
            Bukkit.getPluginManager().getPlugin("rpk-chat-bukkit") as RPKChatBukkit,
            serialized["font"] as? String,
            serialized["color"] as? String,
            serialized["bold"] as? Boolean,
            serialized["italic"] as? Boolean,
            serialized["underlined"] as? Boolean,
            serialized["strikethrough"] as? Boolean,
            serialized["obfuscated"] as? Boolean,
            serialized["insertion"] as? String,
            serialized["hover"] as? HoverAction,
            serialized["click"] as? ClickAction
        )
    }

    override fun toChatComponents(context: DirectedPreFormatMessageContext) = supplyAsync {
        TextComponent.fromLegacyText(getText(context).join()).also {
            for (component in it) {

                if (font != null) component.font = font
                component.color = getChatNameColor(context)
                if (isBold != null) component.isBold = isBold
                if (isItalic != null) component.isItalic = isItalic
                if (isUnderlined != null) component.isUnderlined = isUnderlined
                if (isStrikethrough != null) component.isStrikethrough = isStrikethrough
                if (isObfuscated != null) component.isObfuscated = isObfuscated
                if (insertion != null) component.insertion = insertion
                if (hover != null) component.hoverEvent = hover.toHoverEvent(context).join()
                if (click != null) component.clickEvent = click.toClickEvent(context).join()
            }
        }
    }.exceptionally { exception ->
        plugin.logger.log(Level.SEVERE, "Failed to convert text part to chat components", exception)
        throw exception
    }

    // method to get chat name color
    private fun getChatNameColor(context: DirectedPreFormatMessageContext): ChatColor {
        // check if chat channel is excluded from chat name color functionality
        val excludedChatChannels = plugin.config.getList("rpk_channels_excluded_for_chat_name_color_functionality")
        if (excludedChatChannels != null && !excludedChatChannels.contains(context.chatChannel.name.value) && context.senderMinecraftProfile != null) {
            // try to use chat name color from database
            val minecraftProfile = context.senderMinecraftProfile
            if (minecraftProfile != null) {
                val minecraftProfileId = minecraftProfile.id
                if (minecraftProfileId != null) {
                    val chatNameColorRecord = plugin.database.getTable(RPKChatNameColorTable::class.java)[minecraftProfileId].join()
                    if (chatNameColorRecord != null) {
                        return ChatColor.of(chatNameColorRecord.chatNameColor)
                    }
                }
            }
        }
        return if (color == null) {
            // default color
            ChatColor.WHITE
        } else {
            // color from configuration
            ChatColor.of(color)
        }
    }
}