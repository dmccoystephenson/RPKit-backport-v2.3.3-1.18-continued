/*
 * This file is generated by jOOQ.
*/
package com.rpkit.players.bukkit.database.jooq.rpkit.tables;


import com.rpkit.players.bukkit.database.jooq.rpkit.Keys;
import com.rpkit.players.bukkit.database.jooq.rpkit.Rpkit;
import com.rpkit.players.bukkit.database.jooq.rpkit.tables.records.RpkitMinecraftProfileRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RpkitMinecraftProfile extends TableImpl<RpkitMinecraftProfileRecord> {

    private static final long serialVersionUID = 1330868852;

    /**
     * The reference instance of <code>rpkit.rpkit_minecraft_profile</code>
     */
    public static final RpkitMinecraftProfile RPKIT_MINECRAFT_PROFILE = new RpkitMinecraftProfile();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RpkitMinecraftProfileRecord> getRecordType() {
        return RpkitMinecraftProfileRecord.class;
    }

    /**
     * The column <code>rpkit.rpkit_minecraft_profile.id</code>.
     */
    public final TableField<RpkitMinecraftProfileRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>rpkit.rpkit_minecraft_profile.profile_id</code>.
     */
    public final TableField<RpkitMinecraftProfileRecord, Integer> PROFILE_ID = createField("profile_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>rpkit.rpkit_minecraft_profile.minecraft_uuid</code>.
     */
    public final TableField<RpkitMinecraftProfileRecord, String> MINECRAFT_UUID = createField("minecraft_uuid", org.jooq.impl.SQLDataType.VARCHAR.length(36), this, "");

    /**
     * Create a <code>rpkit.rpkit_minecraft_profile</code> table reference
     */
    public RpkitMinecraftProfile() {
        this("rpkit_minecraft_profile", null);
    }

    /**
     * Create an aliased <code>rpkit.rpkit_minecraft_profile</code> table reference
     */
    public RpkitMinecraftProfile(String alias) {
        this(alias, RPKIT_MINECRAFT_PROFILE);
    }

    private RpkitMinecraftProfile(String alias, Table<RpkitMinecraftProfileRecord> aliased) {
        this(alias, aliased, null);
    }

    private RpkitMinecraftProfile(String alias, Table<RpkitMinecraftProfileRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Rpkit.RPKIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<RpkitMinecraftProfileRecord, Integer> getIdentity() {
        return Keys.IDENTITY_RPKIT_MINECRAFT_PROFILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RpkitMinecraftProfileRecord> getPrimaryKey() {
        return Keys.KEY_RPKIT_MINECRAFT_PROFILE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RpkitMinecraftProfileRecord>> getKeys() {
        return Arrays.<UniqueKey<RpkitMinecraftProfileRecord>>asList(Keys.KEY_RPKIT_MINECRAFT_PROFILE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RpkitMinecraftProfile as(String alias) {
        return new RpkitMinecraftProfile(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RpkitMinecraftProfile rename(String name) {
        return new RpkitMinecraftProfile(name, null);
    }
}
