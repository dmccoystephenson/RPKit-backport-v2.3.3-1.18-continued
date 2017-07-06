/*
 * This file is generated by jOOQ.
*/
package com.rpkit.classes.bukkit.database.jooq.rpkit;


import com.rpkit.classes.bukkit.database.jooq.DefaultCatalog;
import com.rpkit.classes.bukkit.database.jooq.rpkit.tables.RpkitCharacterClass;
import com.rpkit.classes.bukkit.database.jooq.rpkit.tables.RpkitClassExperience;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Rpkit extends SchemaImpl {

    private static final long serialVersionUID = 141705491;

    /**
     * The reference instance of <code>rpkit</code>
     */
    public static final Rpkit RPKIT = new Rpkit();

    /**
     * The table <code>rpkit.rpkit_character_class</code>.
     */
    public final RpkitCharacterClass RPKIT_CHARACTER_CLASS = RpkitCharacterClass.RPKIT_CHARACTER_CLASS;

    /**
     * The table <code>rpkit.rpkit_class_experience</code>.
     */
    public final RpkitClassExperience RPKIT_CLASS_EXPERIENCE = RpkitClassExperience.RPKIT_CLASS_EXPERIENCE;

    /**
     * No further instances allowed
     */
    private Rpkit() {
        super("rpkit", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            RpkitCharacterClass.RPKIT_CHARACTER_CLASS,
            RpkitClassExperience.RPKIT_CLASS_EXPERIENCE);
    }
}