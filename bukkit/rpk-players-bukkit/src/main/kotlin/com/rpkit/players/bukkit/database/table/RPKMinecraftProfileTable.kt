package com.rpkit.players.bukkit.database.table

import com.rpkit.core.database.Database
import com.rpkit.core.database.Table
import com.rpkit.players.bukkit.RPKPlayersBukkit
import com.rpkit.players.bukkit.database.jooq.rpkit.Tables.RPKIT_MINECRAFT_PROFILE
import com.rpkit.players.bukkit.profile.RPKMinecraftProfile
import com.rpkit.players.bukkit.profile.RPKMinecraftProfileImpl
import com.rpkit.players.bukkit.profile.RPKProfile
import com.rpkit.players.bukkit.profile.RPKProfileProvider
import org.bukkit.OfflinePlayer
import org.ehcache.config.builders.CacheConfigurationBuilder
import org.ehcache.config.builders.CacheManagerBuilder
import org.ehcache.config.builders.ResourcePoolsBuilder
import org.jooq.SQLDialect
import org.jooq.impl.DSL.constraint
import org.jooq.impl.SQLDataType
import org.jooq.util.sqlite.SQLiteDataType
import java.util.*


class RPKMinecraftProfileTable(database: Database, private val plugin: RPKPlayersBukkit): Table<RPKMinecraftProfile>(database, RPKMinecraftProfile::class) {

    private val cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true)
    private val cache = cacheManager.createCache("cache",
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Int::class.javaObjectType, RPKMinecraftProfile::class.java,
                    ResourcePoolsBuilder.heap(plugin.server.maxPlayers.toLong())))

    override fun create() {
        database.create
                .createTableIfNotExists(RPKIT_MINECRAFT_PROFILE)
                .column(RPKIT_MINECRAFT_PROFILE.ID, if (database.dialect == SQLDialect.SQLITE) SQLiteDataType.INTEGER.identity(true) else SQLDataType.INTEGER.identity(true))
                .column(RPKIT_MINECRAFT_PROFILE.PROFILE_ID, SQLDataType.INTEGER)
                .column(RPKIT_MINECRAFT_PROFILE.MINECRAFT_UUID, SQLDataType.VARCHAR(36))
                .constraints(
                        constraint("pk_rpkit_minecraft_profile").primaryKey(RPKIT_MINECRAFT_PROFILE.ID)
                )
                .execute()
    }

    override fun applyMigrations() {
        if (database.getTableVersion(this) == null) {
            database.setTableVersion(this, "1.3.0")
        }
    }

    override fun insert(entity: RPKMinecraftProfile): Int {
        database.create
                .insertInto(
                        RPKIT_MINECRAFT_PROFILE,
                        RPKIT_MINECRAFT_PROFILE.PROFILE_ID,
                        RPKIT_MINECRAFT_PROFILE.MINECRAFT_UUID
                )
                .values(
                        entity.profile?.id,
                        entity.minecraftUUID.toString()
                )
                .execute()
        val id = database.create.lastID().toInt()
        entity.id = id
        cache.put(id, entity)
        return id
    }

    override fun update(entity: RPKMinecraftProfile) {
        database.create
                .update(RPKIT_MINECRAFT_PROFILE)
                .set(RPKIT_MINECRAFT_PROFILE.PROFILE_ID, entity.profile?.id)
                .set(RPKIT_MINECRAFT_PROFILE.MINECRAFT_UUID, entity.minecraftUUID.toString())
                .where(RPKIT_MINECRAFT_PROFILE.ID.eq(entity.id))
                .execute()
        cache.put(entity.id, entity)
    }

    override fun get(id: Int): RPKMinecraftProfile? {
        if (cache.containsKey(id)) {
            return cache.get(id)
        } else {
            val result = database.create
                    .select(
                            RPKIT_MINECRAFT_PROFILE.PROFILE_ID,
                            RPKIT_MINECRAFT_PROFILE.MINECRAFT_UUID
                    )
                    .from(RPKIT_MINECRAFT_PROFILE)
                    .where(RPKIT_MINECRAFT_PROFILE.ID.eq(id))
                    .fetchOne() ?: return null
            val profileProvider = plugin.core.serviceManager.getServiceProvider(RPKProfileProvider::class)
            val profileId = result.get(RPKIT_MINECRAFT_PROFILE.PROFILE_ID)
            val profile = if (profileId == null) null else profileProvider.getProfile(profileId)
            val minecraftProfile = RPKMinecraftProfileImpl(
                    id,
                    profile,
                    UUID.fromString(result.get(RPKIT_MINECRAFT_PROFILE.MINECRAFT_UUID))
            )
            cache.put(id, minecraftProfile)
            return minecraftProfile
        }
    }

    fun get(profile: RPKProfile): List<RPKMinecraftProfile> {
        val results = database.create
                .select(RPKIT_MINECRAFT_PROFILE.ID)
                .from(RPKIT_MINECRAFT_PROFILE)
                .where(RPKIT_MINECRAFT_PROFILE.PROFILE_ID.eq(profile.id))
                .fetch()
        val minecraftProfiles = results.map { result ->
            get(result.get(RPKIT_MINECRAFT_PROFILE.ID))
        }.filterNotNull()
        return minecraftProfiles
    }

    fun get(player: OfflinePlayer): RPKMinecraftProfile? {
        val result = database.create
                .select(RPKIT_MINECRAFT_PROFILE.ID)
                .from(RPKIT_MINECRAFT_PROFILE)
                .where(RPKIT_MINECRAFT_PROFILE.MINECRAFT_UUID.eq(player.uniqueId.toString()))
                .fetchOne() ?: return null
        return get(result.get(RPKIT_MINECRAFT_PROFILE.ID))
    }

    override fun delete(entity: RPKMinecraftProfile) {
        database.create
                .deleteFrom(RPKIT_MINECRAFT_PROFILE)
                .where(RPKIT_MINECRAFT_PROFILE.ID.eq(entity.id))
                .execute()
        cache.remove(entity.id)
    }
}