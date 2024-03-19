/*
 * Copyright 2020 Ren Binden
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

package com.rpkit.chat.bukkit.command.setchatnamecolor

import com.rpkit.chat.bukkit.RPKChatBukkit
import com.rpkit.chat.bukkit.database.table.RPKChatNameColorTable
import com.rpkit.core.service.Services
import com.rpkit.players.bukkit.profile.minecraft.RPKMinecraftProfileService
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * SetChatNameColor command.
 * Sets the chat color name for a player.
 */
class SetChatNameColorCommand(private val plugin: RPKChatBukkit) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("rpkit.chat.command.setchatnamecolor")) {
            sender.sendMessage(plugin.messages["no-permission-setchatnamecolor"])
            return true
        }
        if (sender !is Player) {
            sender.sendMessage(plugin.messages["not-from-console"])
            return true
        }
        val minecraftProfileService = Services[RPKMinecraftProfileService::class.java]
        if (minecraftProfileService == null) {
            sender.sendMessage(plugin.messages["no-minecraft-profile-service"])
            return true
        }
        val minecraftProfile = minecraftProfileService.getPreloadedMinecraftProfile(sender)
        if (minecraftProfile == null) {
            sender.sendMessage(plugin.messages["no-minecraft-profile"])
            return true
        }

        // retrieve desired chat name color
        val chatNameColor = args[0]
        if (chatNameColor.isBlank()) {
            sender.sendMessage(plugin.messages["setchatnamecolor-usage"])
            return true
        }
        if (chatNameColor.length > 16) {
            sender.sendMessage(plugin.messages["setchatnamecolor-too-long"])
            return true
        }

        // should match #ffffff (not case sensitive)
        if (!chatNameColor.matches(Regex("#[0-9a-fA-F]{6}"))) {
            sender.sendMessage("Invalid color code. Please use a hex color code, e.g. #ffffff")
            return true
        }

        val minecraftProfileId = minecraftProfile.id
        if (minecraftProfileId == null) {
            sender.sendMessage(plugin.messages["no-minecraft-profile"])
            return true
        }

        // set chat name color for player in `rpkit_chat_name_color` table
        val recordExists = plugin.database.getTable(RPKChatNameColorTable::class.java).get(minecraftProfileId).join() != null
        return if (recordExists) {
            plugin.database.getTable(RPKChatNameColorTable::class.java).update(minecraftProfileId, chatNameColor)
            sender.sendMessage("Your chat name color has been updated!")
            true
        } else {
            plugin.database.getTable(RPKChatNameColorTable::class.java).insert(minecraftProfileId, chatNameColor)
            sender.sendMessage("Your chat name color has been set!")
            true
        }
    }
}