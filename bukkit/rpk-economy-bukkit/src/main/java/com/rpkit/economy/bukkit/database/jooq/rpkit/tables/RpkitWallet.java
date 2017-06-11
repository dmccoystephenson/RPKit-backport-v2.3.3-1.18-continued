/*
 * This file is generated by jOOQ.
*/
package com.rpkit.economy.bukkit.database.jooq.rpkit.tables;


import com.rpkit.economy.bukkit.database.jooq.rpkit.Keys;
import com.rpkit.economy.bukkit.database.jooq.rpkit.Rpkit;
import com.rpkit.economy.bukkit.database.jooq.rpkit.tables.records.RpkitWalletRecord;

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
public class RpkitWallet extends TableImpl<RpkitWalletRecord> {

    private static final long serialVersionUID = 549312763;

    /**
     * The reference instance of <code>rpkit.rpkit_wallet</code>
     */
    public static final RpkitWallet RPKIT_WALLET = new RpkitWallet();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RpkitWalletRecord> getRecordType() {
        return RpkitWalletRecord.class;
    }

    /**
     * The column <code>rpkit.rpkit_wallet.id</code>.
     */
    public final TableField<RpkitWalletRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>rpkit.rpkit_wallet.character_id</code>.
     */
    public final TableField<RpkitWalletRecord, Integer> CHARACTER_ID = createField("character_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>rpkit.rpkit_wallet.currency_id</code>.
     */
    public final TableField<RpkitWalletRecord, Integer> CURRENCY_ID = createField("currency_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>rpkit.rpkit_wallet.balance</code>.
     */
    public final TableField<RpkitWalletRecord, Integer> BALANCE = createField("balance", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>rpkit.rpkit_wallet</code> table reference
     */
    public RpkitWallet() {
        this("rpkit_wallet", null);
    }

    /**
     * Create an aliased <code>rpkit.rpkit_wallet</code> table reference
     */
    public RpkitWallet(String alias) {
        this(alias, RPKIT_WALLET);
    }

    private RpkitWallet(String alias, Table<RpkitWalletRecord> aliased) {
        this(alias, aliased, null);
    }

    private RpkitWallet(String alias, Table<RpkitWalletRecord> aliased, Field<?>[] parameters) {
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
    public Identity<RpkitWalletRecord, Integer> getIdentity() {
        return Keys.IDENTITY_RPKIT_WALLET;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RpkitWalletRecord> getPrimaryKey() {
        return Keys.KEY_RPKIT_WALLET_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RpkitWalletRecord>> getKeys() {
        return Arrays.<UniqueKey<RpkitWalletRecord>>asList(Keys.KEY_RPKIT_WALLET_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RpkitWallet as(String alias) {
        return new RpkitWallet(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RpkitWallet rename(String name) {
        return new RpkitWallet(name, null);
    }
}
