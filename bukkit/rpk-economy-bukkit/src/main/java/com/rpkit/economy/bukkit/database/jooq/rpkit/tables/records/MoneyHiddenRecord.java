/*
 * This file is generated by jOOQ.
*/
package com.rpkit.economy.bukkit.database.jooq.rpkit.tables.records;


import com.rpkit.economy.bukkit.database.jooq.rpkit.tables.MoneyHidden;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

import javax.annotation.Generated;


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
public class MoneyHiddenRecord extends UpdatableRecordImpl<MoneyHiddenRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = 1169879720;

    /**
     * Setter for <code>rpkit.money_hidden.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>rpkit.money_hidden.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>rpkit.money_hidden.character_id</code>.
     */
    public void setCharacterId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>rpkit.money_hidden.character_id</code>.
     */
    public Integer getCharacterId() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return MoneyHidden.MONEY_HIDDEN.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return MoneyHidden.MONEY_HIDDEN.CHARACTER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getCharacterId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoneyHiddenRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoneyHiddenRecord value2(Integer value) {
        setCharacterId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoneyHiddenRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MoneyHiddenRecord
     */
    public MoneyHiddenRecord() {
        super(MoneyHidden.MONEY_HIDDEN);
    }

    /**
     * Create a detached, initialised MoneyHiddenRecord
     */
    public MoneyHiddenRecord(Integer id, Integer characterId) {
        super(MoneyHidden.MONEY_HIDDEN);

        set(0, id);
        set(1, characterId);
    }
}
