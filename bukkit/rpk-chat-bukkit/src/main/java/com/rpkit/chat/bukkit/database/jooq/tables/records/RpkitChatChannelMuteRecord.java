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

/*
 * This file is generated by jOOQ.
 */
package com.rpkit.chat.bukkit.database.jooq.tables.records;


import com.rpkit.chat.bukkit.database.jooq.tables.RpkitChatChannelMute;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RpkitChatChannelMuteRecord extends TableRecordImpl<RpkitChatChannelMuteRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = -1827493588;

    /**
     * Setter for <code>rpkit_chat.rpkit_chat_channel_mute.minecraft_profile_id</code>.
     */
    public void setMinecraftProfileId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>rpkit_chat.rpkit_chat_channel_mute.minecraft_profile_id</code>.
     */
    public Integer getMinecraftProfileId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>rpkit_chat.rpkit_chat_channel_mute.chat_channel_name</code>.
     */
    public void setChatChannelName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>rpkit_chat.rpkit_chat_channel_mute.chat_channel_name</code>.
     */
    public String getChatChannelName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return RpkitChatChannelMute.RPKIT_CHAT_CHANNEL_MUTE.MINECRAFT_PROFILE_ID;
    }

    @Override
    public Field<String> field2() {
        return RpkitChatChannelMute.RPKIT_CHAT_CHANNEL_MUTE.CHAT_CHANNEL_NAME;
    }

    @Override
    public Integer component1() {
        return getMinecraftProfileId();
    }

    @Override
    public String component2() {
        return getChatChannelName();
    }

    @Override
    public Integer value1() {
        return getMinecraftProfileId();
    }

    @Override
    public String value2() {
        return getChatChannelName();
    }

    @Override
    public RpkitChatChannelMuteRecord value1(Integer value) {
        setMinecraftProfileId(value);
        return this;
    }

    @Override
    public RpkitChatChannelMuteRecord value2(String value) {
        setChatChannelName(value);
        return this;
    }

    @Override
    public RpkitChatChannelMuteRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RpkitChatChannelMuteRecord
     */
    public RpkitChatChannelMuteRecord() {
        super(RpkitChatChannelMute.RPKIT_CHAT_CHANNEL_MUTE);
    }

    /**
     * Create a detached, initialised RpkitChatChannelMuteRecord
     */
    public RpkitChatChannelMuteRecord(Integer minecraftProfileId, String chatChannelName) {
        super(RpkitChatChannelMute.RPKIT_CHAT_CHANNEL_MUTE);

        set(0, minecraftProfileId);
        set(1, chatChannelName);
    }
}