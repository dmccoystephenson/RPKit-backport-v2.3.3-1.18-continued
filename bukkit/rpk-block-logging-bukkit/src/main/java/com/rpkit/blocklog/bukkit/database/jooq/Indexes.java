/*
 * This file is generated by jOOQ.
 */
package com.rpkit.blocklog.bukkit.database.jooq;


import com.rpkit.blocklog.bukkit.database.jooq.tables.FlywaySchemaHistoryBlocklogging;
import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in rpkit_block_logging.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index FLYWAY_SCHEMA_HISTORY_BLOCKLOGGING_FLYWAY_SCHEMA_HISTORY_BLOCKLOGGING_S_IDX = Internal.createIndex(DSL.name("flyway_schema_history_blocklogging_s_idx"), FlywaySchemaHistoryBlocklogging.FLYWAY_SCHEMA_HISTORY_BLOCKLOGGING, new OrderField[] { FlywaySchemaHistoryBlocklogging.FLYWAY_SCHEMA_HISTORY_BLOCKLOGGING.SUCCESS }, false);
}