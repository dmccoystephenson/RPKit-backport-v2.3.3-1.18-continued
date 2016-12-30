/*
 * Copyright 2016 Ross Binden
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

package com.rpkit.chat.bukkit.database.table

import com.rpkit.chat.bukkit.RPKChatBukkit
import com.rpkit.chat.bukkit.chatchannel.RPKChatChannelProvider
import com.rpkit.chat.bukkit.speaker.RPKChatChannelSpeaker
import com.rpkit.core.database.Database
import com.rpkit.core.database.Table
import com.rpkit.core.database.use
import com.rpkit.players.bukkit.player.RPKPlayer
import com.rpkit.players.bukkit.player.RPKPlayerProvider
import org.ehcache.Cache
import org.ehcache.CacheManager
import org.ehcache.config.builders.CacheConfigurationBuilder
import org.ehcache.config.builders.CacheManagerBuilder
import org.ehcache.config.builders.ResourcePoolsBuilder
import java.sql.Statement.RETURN_GENERATED_KEYS

/**
 * Represents the chat channel speaker table
 */
class RPKChatChannelSpeakerTable: Table<RPKChatChannelSpeaker> {

    private val plugin: RPKChatBukkit
    private val cacheManager: CacheManager
    private val cache: Cache<Int, RPKChatChannelSpeaker>

    constructor(database: Database, plugin: RPKChatBukkit): super(database, RPKChatChannelSpeaker::class) {
        this.plugin = plugin
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true)
        cache = cacheManager.createCache("cache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Int::class.javaObjectType, RPKChatChannelSpeaker::class.java,
                        ResourcePoolsBuilder.heap(plugin.server.maxPlayers.toLong())).build())
    }

    override fun create() {
        database.createConnection().use { connection ->
            connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS rpkit_chat_channel_speaker(" +
                            "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                            "player_id INTEGER," +
                            "chat_channel_id INTEGER" +
                    ")"
            ).use { statement ->
                statement.executeUpdate()
            }
        }
    }

    override fun applyMigrations() {
        if (database.getTableVersion(this) == null) {
            database.setTableVersion(this, "0.4.0")
        }
    }

    override fun insert(entity: RPKChatChannelSpeaker): Int {
        var id = 0
        database.createConnection().use { connection ->
            connection.prepareStatement(
                    "INSERT INTO rpkit_chat_channel_speaker(player_id, chat_channel_id) VALUES(?, ?)",
                    RETURN_GENERATED_KEYS
            ).use { statement ->
                statement.setInt(1, entity.player.id)
                statement.setInt(2, entity.chatChannel.id)
                statement.executeUpdate()
                val generatedKeys = statement.generatedKeys
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1)
                    entity.id = id
                    cache.put(id, entity)
                }
            }
        }
        return id
    }

    override fun update(entity: RPKChatChannelSpeaker) {
        database.createConnection().use { connection ->
            connection.prepareStatement(
                    "UPDATE rpkit_chat_channel_speaker SET player_id = ?, chat_channel_id = ? WHERE id = ?"
            ).use { statement ->
                statement.setInt(1, entity.player.id)
                statement.setInt(2, entity.chatChannel.id)
                statement.setInt(3, entity.id)
                statement.executeUpdate()
                cache.put(entity.id, entity)
            }
        }
    }

    override fun get(id: Int): RPKChatChannelSpeaker? {
        if (cache.containsKey(id)) {
            return cache.get(id)
        } else {
            var chatChannelSpeaker: RPKChatChannelSpeaker? = null
            database.createConnection().use { connection ->
                connection.prepareStatement(
                        "SELECT id, player_id, chat_channel_id FROM rpkit_chat_channel_speaker WHERE id = ?"
                ).use { statement ->
                    statement.setInt(1, id)
                    val resultSet = statement.executeQuery()
                    if (resultSet.next()) {
                        val playerProvider = plugin.core.serviceManager.getServiceProvider(RPKPlayerProvider::class)
                        val chatChannelProvider = plugin.core.serviceManager.getServiceProvider(RPKChatChannelProvider::class)
                        chatChannelSpeaker = RPKChatChannelSpeaker(
                                resultSet.getInt("id"),
                                playerProvider.getPlayer(resultSet.getInt("player_id"))!!,
                                chatChannelProvider.getChatChannel(resultSet.getInt("chat_channel_id"))!!
                        )
                        cache.put(id, chatChannelSpeaker)
                    }
                }
            }
            return chatChannelSpeaker
        }
    }

    /**
     * Gets the speaker instance for a player, or null if the player is not speaking in a channel.
     *
     * @param player The player
     * @return The chat channel speaker instance, or null if the player is not currently speaking in a channel.
     */
    fun get(player: RPKPlayer): RPKChatChannelSpeaker? {
        var chatChannelSpeaker: RPKChatChannelSpeaker? = null
        database.createConnection().use { connection ->
            connection.prepareStatement(
                    "SELECT id, player_id, chat_channel_id FROM rpkit_chat_channel_speaker WHERE player_id = ?"
            ).use { statement ->
                statement.setInt(1, player.id)
                val resultSet = statement.executeQuery()
                if (resultSet.next()) {
                    val playerProvider = plugin.core.serviceManager.getServiceProvider(RPKPlayerProvider::class)
                    val chatChannelProvider = plugin.core.serviceManager.getServiceProvider(RPKChatChannelProvider::class)
                    val finalChatChannelSpeaker = RPKChatChannelSpeaker(resultSet.getInt("id"),
                            playerProvider.getPlayer(resultSet.getInt("player_id"))!!,
                            chatChannelProvider.getChatChannel(resultSet.getInt("chat_channel_id"))!!
                    )
                    chatChannelSpeaker = finalChatChannelSpeaker
                    cache.put(finalChatChannelSpeaker.id, finalChatChannelSpeaker)
                }
            }
        }
        return chatChannelSpeaker
    }

    override fun delete(entity: RPKChatChannelSpeaker) {
        database.createConnection().use { connection ->
            connection.prepareStatement(
                    "DELETE FROM rpkit_chat_channel_speaker WHERE id = ?"
            ).use { statement ->
                statement.setInt(1, entity.id)
                statement.executeUpdate()
                cache.remove(entity.id)
            }
        }
    }


}