package com.rpkit.essentials.bukkit.database.table

import com.rpkit.core.database.Database
import com.rpkit.core.database.Table
import com.rpkit.core.database.use
import com.rpkit.essentials.bukkit.RPKEssentialsBukkit
import com.rpkit.essentials.bukkit.logmessage.RPKLogMessagesEnabled
import com.rpkit.players.bukkit.player.RPKPlayer
import com.rpkit.players.bukkit.player.RPKPlayerProvider
import java.sql.PreparedStatement
import java.sql.Statement.RETURN_GENERATED_KEYS


class RPKLogMessagesEnabledTable(database: Database, private val plugin: RPKEssentialsBukkit): Table<RPKLogMessagesEnabled>(database, RPKLogMessagesEnabled::class) {

    override fun create() {
        database.createConnection().use { connection ->
            connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS rpkit_log_messages_enabled(" +
                            "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                            "player_id INTEGER," +
                            "enabled BOOLEAN" +
                    ")"
            ).use(PreparedStatement::executeUpdate)
        }
    }

    override fun applyMigrations() {
        if (database.getTableVersion(this) == null) {
            database.setTableVersion(this, "1.1.0")
        }
    }

    override fun insert(entity: RPKLogMessagesEnabled): Int {
        var id = 0
        database.createConnection().use { connection ->
            connection.prepareStatement(
                    "INSERT INTO rpkit_log_messages_enabled(player_id, enabled) VALUES(?, ?)",
                    RETURN_GENERATED_KEYS
            ).use { statement ->
                statement.setInt(1, entity.player.id)
                statement.setBoolean(2, entity.enabled)
                statement.executeUpdate()
                val generatedKeys = statement.generatedKeys
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1)
                    entity.id = id
                }
            }
        }
        return id
    }

    override fun update(entity: RPKLogMessagesEnabled) {
        database.createConnection().use { connection ->
            connection.prepareStatement(
                    "UPDATE rpkit_log_messages_enabled SET player_id = ?, enabled = ? WHERE id = ?"
            ).use { statement ->
                statement.setInt(1, entity.player.id)
                statement.setBoolean(2, entity.enabled)
                statement.setInt(3, entity.id)
                statement.executeUpdate()
            }
        }
    }

    override fun get(id: Int): RPKLogMessagesEnabled? {
        var logMessagesEnabled: RPKLogMessagesEnabled? = null
        database.createConnection().use { connection ->
            connection.prepareStatement(
                    "SELECT id, player_id, enabled FROM rpkit_log_messages_enabled WHERE id = ?"
            ).use { statement ->
                statement.setInt(1, id)
                val resultSet = statement.executeQuery()
                if (resultSet.next()) {
                    logMessagesEnabled = RPKLogMessagesEnabled(
                            resultSet.getInt("id"),
                            plugin.core.serviceManager.getServiceProvider(RPKPlayerProvider::class).getPlayer(resultSet.getInt("player_id"))!!,
                            resultSet.getBoolean("enabled")
                    )
                }
            }
        }
        return logMessagesEnabled
    }

    fun get(player: RPKPlayer): RPKLogMessagesEnabled? {
        var logMessagesEnabled: RPKLogMessagesEnabled? = null
        database.createConnection().use { connection ->
            connection.prepareStatement(
                    "SELECT id, player_id, enabled FROM rpkit_log_messages_enabled WHERE player_id = ?"
            ).use { statement ->
                statement.setInt(1, player.id)
                val resultSet = statement.executeQuery()
                if (resultSet.next()) {
                    logMessagesEnabled = RPKLogMessagesEnabled(
                            resultSet.getInt("id"),
                            plugin.core.serviceManager.getServiceProvider(RPKPlayerProvider::class).getPlayer(resultSet.getInt("player_id"))!!,
                            resultSet.getBoolean("enabled")
                    )
                }
            }
        }
        return logMessagesEnabled
    }

    override fun delete(entity: RPKLogMessagesEnabled) {
        database.createConnection().use { connection ->
            connection.prepareStatement(
                    "DELETE FROM rpkit_log_messages_enabled WHERE id = ?"
            ).use { statement ->
                statement.setInt(1, entity.id)
                statement.executeUpdate()
            }
        }
    }

}