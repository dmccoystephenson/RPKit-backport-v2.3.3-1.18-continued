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
package com.rpkit.characters.bukkit.database.jooq;


import com.rpkit.characters.bukkit.database.jooq.tables.RpkitCharacter;
import com.rpkit.characters.bukkit.database.jooq.tables.RpkitNewCharacterCooldown;
import com.rpkit.characters.bukkit.database.jooq.tables.RpkitRace;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RpkitCharacters extends SchemaImpl {

    private static final long serialVersionUID = -930078648;

    /**
     * The reference instance of <code>rpkit_characters</code>
     */
    public static final RpkitCharacters RPKIT_CHARACTERS = new RpkitCharacters();

    /**
     * The table <code>rpkit_characters.rpkit_character</code>.
     */
    public final RpkitCharacter RPKIT_CHARACTER = RpkitCharacter.RPKIT_CHARACTER;

    /**
     * The table <code>rpkit_characters.rpkit_new_character_cooldown</code>.
     */
    public final RpkitNewCharacterCooldown RPKIT_NEW_CHARACTER_COOLDOWN = RpkitNewCharacterCooldown.RPKIT_NEW_CHARACTER_COOLDOWN;

    /**
     * The table <code>rpkit_characters.rpkit_race</code>.
     */
    public final RpkitRace RPKIT_RACE = RpkitRace.RPKIT_RACE;

    /**
     * No further instances allowed
     */
    private RpkitCharacters() {
        super("rpkit_characters", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            RpkitCharacter.RPKIT_CHARACTER,
            RpkitNewCharacterCooldown.RPKIT_NEW_CHARACTER_COOLDOWN,
            RpkitRace.RPKIT_RACE);
    }
}