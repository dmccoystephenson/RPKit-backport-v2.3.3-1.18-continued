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

package com.rpkit.chat.bukkit.irc.listener

import com.rpkit.chat.bukkit.RPKChatBukkit
import com.rpkit.chat.bukkit.chatchannel.RPKChatChannelService
import com.rpkit.chat.bukkit.chatchannel.format.part.SenderCharacterNamePart
import com.rpkit.chat.bukkit.chatchannel.undirected.IRCComponent
import com.rpkit.chat.bukkit.database.table.RPKChatNameColorTable
import com.rpkit.chat.bukkit.irc.IRCChannel
import com.rpkit.core.service.Services
import com.rpkit.players.bukkit.profile.RPKProfileName
import com.rpkit.players.bukkit.profile.RPKProfileService
import com.rpkit.players.bukkit.profile.irc.RPKIRCNick
import com.rpkit.players.bukkit.profile.irc.RPKIRCProfileService
import com.rpkit.players.bukkit.profile.minecraft.RPKMinecraftProfileId
import org.pircbotx.hooks.ListenerAdapter
import org.pircbotx.hooks.events.MessageEvent
import java.util.concurrent.CompletableFuture
import java.util.logging.Level

/**
 * IRC message listener.
 * Sends messages to chat channels when received in IRC.
 */
class IRCMessageListener(private val plugin: RPKChatBukkit) : ListenerAdapter() {

    override fun onMessage(event: MessageEvent) {
        // Commands all extend ListenerAdapter as well, and have their own handling.
        // This stops commands from being sent to chat.
        if (event.message.startsWith("!")) return

        val profileService = Services[RPKProfileService::class.java] ?: return
        val ircProfileService = Services[RPKIRCProfileService::class.java] ?: return
        // According to PircBotX documentation, user can be null if the hostmask doesn't match a user at creation time.
        val user = event.user ?: return
        CompletableFuture.runAsync {
            val senderIRCProfile = ircProfileService.getIRCProfile(RPKIRCNick(user.nick)).join()
                ?: ircProfileService.createIRCProfile(
                    profileService.createThinProfile(RPKProfileName(user.nick)),
                    RPKIRCNick(user.nick)
                ).join()
            val senderProfile = senderIRCProfile.profile
            val chatChannelService = Services[RPKChatChannelService::class.java] ?: return@runAsync
            val chatChannel = chatChannelService.getChatChannelFromIRCChannel(IRCChannel(event.channel.name))
            // for each part in format
            // if part is a sender character name part
            // set color to red
            val chatChannelFormatIterator = chatChannel?.format?.iterator() ?: return@runAsync
            while (chatChannelFormatIterator.hasNext()) {
                val formatPart = chatChannelFormatIterator.next()
                if (formatPart is SenderCharacterNamePart) {
                    formatPart.color = "#ffffff"
                    break
                }
            }

            chatChannel.sendMessage(
                senderProfile,
                null,
                event.message,
                chatChannel.directedPreFormatPipeline,
                chatChannel.format,
                chatChannel.directedPostFormatPipeline,
                chatChannel.undirectedPipeline.filter { it !is IRCComponent }
            )
        }.exceptionally { exception ->
            plugin.logger.log(Level.SEVERE, "Failed to send message from IRC", exception)
            throw exception
        }
    }

}