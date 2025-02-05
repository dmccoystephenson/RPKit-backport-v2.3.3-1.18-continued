/*
 * This file is generated by jOOQ.
 */
package com.rpkit.chat.bukkit.database.jooq.tables.records;


import com.rpkit.chat.bukkit.database.jooq.tables.RpkitLastUsedChatGroup;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RpkitLastUsedChatGroupRecord extends TableRecordImpl<RpkitLastUsedChatGroupRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>rpkit_chat.rpkit_last_used_chat_group.minecraft_profile_id</code>.
     */
    public void setMinecraftProfileId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for
     * <code>rpkit_chat.rpkit_last_used_chat_group.minecraft_profile_id</code>.
     */
    public Integer getMinecraftProfileId() {
        return (Integer) get(0);
    }

    /**
     * Setter for
     * <code>rpkit_chat.rpkit_last_used_chat_group.chat_group_id</code>.
     */
    public void setChatGroupId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for
     * <code>rpkit_chat.rpkit_last_used_chat_group.chat_group_id</code>.
     */
    public Integer getChatGroupId() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return RpkitLastUsedChatGroup.RPKIT_LAST_USED_CHAT_GROUP.MINECRAFT_PROFILE_ID;
    }

    @Override
    public Field<Integer> field2() {
        return RpkitLastUsedChatGroup.RPKIT_LAST_USED_CHAT_GROUP.CHAT_GROUP_ID;
    }

    @Override
    public Integer component1() {
        return getMinecraftProfileId();
    }

    @Override
    public Integer component2() {
        return getChatGroupId();
    }

    @Override
    public Integer value1() {
        return getMinecraftProfileId();
    }

    @Override
    public Integer value2() {
        return getChatGroupId();
    }

    @Override
    public RpkitLastUsedChatGroupRecord value1(Integer value) {
        setMinecraftProfileId(value);
        return this;
    }

    @Override
    public RpkitLastUsedChatGroupRecord value2(Integer value) {
        setChatGroupId(value);
        return this;
    }

    @Override
    public RpkitLastUsedChatGroupRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RpkitLastUsedChatGroupRecord
     */
    public RpkitLastUsedChatGroupRecord() {
        super(RpkitLastUsedChatGroup.RPKIT_LAST_USED_CHAT_GROUP);
    }

    /**
     * Create a detached, initialised RpkitLastUsedChatGroupRecord
     */
    public RpkitLastUsedChatGroupRecord(Integer minecraftProfileId, Integer chatGroupId) {
        super(RpkitLastUsedChatGroup.RPKIT_LAST_USED_CHAT_GROUP);

        setMinecraftProfileId(minecraftProfileId);
        setChatGroupId(chatGroupId);
    }
}
