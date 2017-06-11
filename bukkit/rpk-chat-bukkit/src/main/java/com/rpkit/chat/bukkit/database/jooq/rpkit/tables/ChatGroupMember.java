/*
 * This file is generated by jOOQ.
*/
package com.rpkit.chat.bukkit.database.jooq.rpkit.tables;


import com.rpkit.chat.bukkit.database.jooq.rpkit.Keys;
import com.rpkit.chat.bukkit.database.jooq.rpkit.Rpkit;
import com.rpkit.chat.bukkit.database.jooq.rpkit.tables.records.ChatGroupMemberRecord;

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
public class ChatGroupMember extends TableImpl<ChatGroupMemberRecord> {

    private static final long serialVersionUID = 764074313;

    /**
     * The reference instance of <code>rpkit.chat_group_member</code>
     */
    public static final ChatGroupMember CHAT_GROUP_MEMBER = new ChatGroupMember();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ChatGroupMemberRecord> getRecordType() {
        return ChatGroupMemberRecord.class;
    }

    /**
     * The column <code>rpkit.chat_group_member.id</code>.
     */
    public final TableField<ChatGroupMemberRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>rpkit.chat_group_member.chat_group_id</code>.
     */
    public final TableField<ChatGroupMemberRecord, Integer> CHAT_GROUP_ID = createField("chat_group_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>rpkit.chat_group_member.minecraft_profile_id</code>.
     */
    public final TableField<ChatGroupMemberRecord, Integer> MINECRAFT_PROFILE_ID = createField("minecraft_profile_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>rpkit.chat_group_member</code> table reference
     */
    public ChatGroupMember() {
        this("chat_group_member", null);
    }

    /**
     * Create an aliased <code>rpkit.chat_group_member</code> table reference
     */
    public ChatGroupMember(String alias) {
        this(alias, CHAT_GROUP_MEMBER);
    }

    private ChatGroupMember(String alias, Table<ChatGroupMemberRecord> aliased) {
        this(alias, aliased, null);
    }

    private ChatGroupMember(String alias, Table<ChatGroupMemberRecord> aliased, Field<?>[] parameters) {
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
    public Identity<ChatGroupMemberRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CHAT_GROUP_MEMBER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ChatGroupMemberRecord> getPrimaryKey() {
        return Keys.KEY_CHAT_GROUP_MEMBER_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ChatGroupMemberRecord>> getKeys() {
        return Arrays.<UniqueKey<ChatGroupMemberRecord>>asList(Keys.KEY_CHAT_GROUP_MEMBER_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChatGroupMember as(String alias) {
        return new ChatGroupMember(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ChatGroupMember rename(String name) {
        return new ChatGroupMember(name, null);
    }
}
