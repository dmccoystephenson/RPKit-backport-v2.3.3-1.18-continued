/*
 * This file is generated by jOOQ.
*/
package com.rpkit.auctions.bukkit.database.jooq.rpkit.tables;


import com.rpkit.auctions.bukkit.database.jooq.rpkit.Keys;
import com.rpkit.auctions.bukkit.database.jooq.rpkit.Rpkit;
import com.rpkit.auctions.bukkit.database.jooq.rpkit.tables.records.RpkitBidRecord;

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
public class RpkitBid extends TableImpl<RpkitBidRecord> {

    private static final long serialVersionUID = 1445903331;

    /**
     * The reference instance of <code>rpkit.rpkit_bid</code>
     */
    public static final RpkitBid RPKIT_BID = new RpkitBid();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RpkitBidRecord> getRecordType() {
        return RpkitBidRecord.class;
    }

    /**
     * The column <code>rpkit.rpkit_bid.id</code>.
     */
    public final TableField<RpkitBidRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>rpkit.rpkit_bid.auction_id</code>.
     */
    public final TableField<RpkitBidRecord, Integer> AUCTION_ID = createField("auction_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>rpkit.rpkit_bid.character_id</code>.
     */
    public final TableField<RpkitBidRecord, Integer> CHARACTER_ID = createField("character_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>rpkit.rpkit_bid.amount</code>.
     */
    public final TableField<RpkitBidRecord, Integer> AMOUNT = createField("amount", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>rpkit.rpkit_bid</code> table reference
     */
    public RpkitBid() {
        this("rpkit_bid", null);
    }

    /**
     * Create an aliased <code>rpkit.rpkit_bid</code> table reference
     */
    public RpkitBid(String alias) {
        this(alias, RPKIT_BID);
    }

    private RpkitBid(String alias, Table<RpkitBidRecord> aliased) {
        this(alias, aliased, null);
    }

    private RpkitBid(String alias, Table<RpkitBidRecord> aliased, Field<?>[] parameters) {
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
    public Identity<RpkitBidRecord, Integer> getIdentity() {
        return Keys.IDENTITY_RPKIT_BID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RpkitBidRecord> getPrimaryKey() {
        return Keys.KEY_RPKIT_BID_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RpkitBidRecord>> getKeys() {
        return Arrays.<UniqueKey<RpkitBidRecord>>asList(Keys.KEY_RPKIT_BID_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RpkitBid as(String alias) {
        return new RpkitBid(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RpkitBid rename(String name) {
        return new RpkitBid(name, null);
    }
}
