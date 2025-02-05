/*
 * This file is generated by jOOQ.
 */
package com.rpkit.players.bukkit.database.jooq.tables.records;


import com.rpkit.players.bukkit.database.jooq.tables.RpkitMinecraftProfile;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RpkitMinecraftProfileRecord extends UpdatableRecordImpl<RpkitMinecraftProfileRecord> implements Record3<Integer, Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>rpkit_players.rpkit_minecraft_profile.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>rpkit_players.rpkit_minecraft_profile.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>rpkit_players.rpkit_minecraft_profile.profile_id</code>.
     */
    public void setProfileId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>rpkit_players.rpkit_minecraft_profile.profile_id</code>.
     */
    public Integer getProfileId() {
        return (Integer) get(1);
    }

    /**
     * Setter for
     * <code>rpkit_players.rpkit_minecraft_profile.minecraft_uuid</code>.
     */
    public void setMinecraftUuid(String value) {
        set(2, value);
    }

    /**
     * Getter for
     * <code>rpkit_players.rpkit_minecraft_profile.minecraft_uuid</code>.
     */
    public String getMinecraftUuid() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, Integer, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return RpkitMinecraftProfile.RPKIT_MINECRAFT_PROFILE.ID;
    }

    @Override
    public Field<Integer> field2() {
        return RpkitMinecraftProfile.RPKIT_MINECRAFT_PROFILE.PROFILE_ID;
    }

    @Override
    public Field<String> field3() {
        return RpkitMinecraftProfile.RPKIT_MINECRAFT_PROFILE.MINECRAFT_UUID;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getProfileId();
    }

    @Override
    public String component3() {
        return getMinecraftUuid();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getProfileId();
    }

    @Override
    public String value3() {
        return getMinecraftUuid();
    }

    @Override
    public RpkitMinecraftProfileRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public RpkitMinecraftProfileRecord value2(Integer value) {
        setProfileId(value);
        return this;
    }

    @Override
    public RpkitMinecraftProfileRecord value3(String value) {
        setMinecraftUuid(value);
        return this;
    }

    @Override
    public RpkitMinecraftProfileRecord values(Integer value1, Integer value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RpkitMinecraftProfileRecord
     */
    public RpkitMinecraftProfileRecord() {
        super(RpkitMinecraftProfile.RPKIT_MINECRAFT_PROFILE);
    }

    /**
     * Create a detached, initialised RpkitMinecraftProfileRecord
     */
    public RpkitMinecraftProfileRecord(Integer id, Integer profileId, String minecraftUuid) {
        super(RpkitMinecraftProfile.RPKIT_MINECRAFT_PROFILE);

        setId(id);
        setProfileId(profileId);
        setMinecraftUuid(minecraftUuid);
    }
}
