/*
 * Copyright 2016 Ross Binden
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

package com.rpkit.auctions.bukkit.bid

import com.rpkit.auctions.bukkit.RPKAuctionsBukkit
import com.rpkit.auctions.bukkit.auction.RPKAuction
import com.rpkit.auctions.bukkit.database.table.RPKBidTable

/**
 * Bid provider implementation.
 */
class RPKBidProviderImpl(private val plugin: RPKAuctionsBukkit): RPKBidProvider {

    override fun addBid(bid: RPKBid) {
        plugin.core.database.getTable(RPKBidTable::class).insert(bid)
    }

    override fun updateBid(bid: RPKBid) {
        plugin.core.database.getTable(RPKBidTable::class).update(bid)
    }

    override fun removeBid(bid: RPKBid) {
        plugin.core.database.getTable(RPKBidTable::class).delete(bid)
    }

    override fun getBids(auction: RPKAuction): List<RPKBid> {
        return plugin.core.database.getTable(RPKBidTable::class).get(auction)
    }

}