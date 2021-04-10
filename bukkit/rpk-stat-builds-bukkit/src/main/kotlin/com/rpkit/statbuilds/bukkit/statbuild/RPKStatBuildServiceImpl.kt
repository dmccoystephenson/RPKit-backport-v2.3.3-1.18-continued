/*
 * Copyright 2021 Ren Binden
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

package com.rpkit.statbuilds.bukkit.statbuild

import com.rpkit.characters.bukkit.character.RPKCharacter
import com.rpkit.core.expression.RPKExpressionService
import com.rpkit.core.service.Services
import com.rpkit.experience.bukkit.experience.RPKExperienceService
import com.rpkit.statbuilds.bukkit.RPKStatBuildsBukkit
import com.rpkit.statbuilds.bukkit.database.table.RPKCharacterStatPointsTable
import com.rpkit.statbuilds.bukkit.statattribute.RPKStatAttribute
import com.rpkit.statbuilds.bukkit.statattribute.RPKStatAttributeService
import java.util.concurrent.CompletableFuture

class RPKStatBuildServiceImpl(override val plugin: RPKStatBuildsBukkit) : RPKStatBuildService {

    override fun getStatPoints(character: RPKCharacter, statAttribute: RPKStatAttribute): CompletableFuture<Int> {
        return plugin.database.getTable(RPKCharacterStatPointsTable::class.java)[character, statAttribute].thenApply { statPoints ->
            statPoints?.points ?: 0
        }
    }

    override fun setStatPoints(character: RPKCharacter, statAttribute: RPKStatAttribute, amount: Int): CompletableFuture<Void> {
        val characterStatPointsTable = plugin.database.getTable(RPKCharacterStatPointsTable::class.java)
        return characterStatPointsTable[character, statAttribute].thenAcceptAsync { characterStatPoints ->
            if (characterStatPoints == null) {
                characterStatPointsTable.insert(RPKCharacterStatPoints(
                    character = character,
                    statAttribute = statAttribute,
                    points = amount
                )).join()
            } else {
                characterStatPoints.points = amount
                characterStatPointsTable.update(characterStatPoints).join()
            }
        }
    }

    override fun getMaxStatPoints(character: RPKCharacter, statAttribute: RPKStatAttribute): CompletableFuture<Int> {
        val expressionService = Services[RPKExpressionService::class.java] ?: return CompletableFuture.completedFuture(0)
        val expression = expressionService.createExpression(plugin.config.getString("stat-attributes.${statAttribute.name}.max-points") ?: return CompletableFuture.completedFuture(0))
        return CompletableFuture.supplyAsync {
            return@supplyAsync expression.parseInt(
                mapOf(
                    "level" to (Services[RPKExperienceService::class.java]?.getLevel(character)?.join()?.toDouble() ?: 1.0)
                )
            ) ?: 0
        }
    }

    override fun getTotalStatPoints(character: RPKCharacter): CompletableFuture<Int> {
        val expressionService = Services[RPKExpressionService::class.java] ?: return CompletableFuture.completedFuture(0)
        val expression = expressionService.createExpression(plugin.config.getString("stat-attribute-points-formula") ?: return CompletableFuture.completedFuture(0))
        return CompletableFuture.supplyAsync {
            expression.parseInt(mapOf(
                "level" to (Services[RPKExperienceService::class.java]?.getLevel(character)?.join()?.toDouble() ?: 1.0)
            )) ?: 0
        }
    }

    override fun getUnassignedStatPoints(character: RPKCharacter): CompletableFuture<Int> {
        return CompletableFuture.supplyAsync {
            getTotalStatPoints(character).join() - getAssignedStatPoints(character).join()
        }
    }

    override fun getAssignedStatPoints(character: RPKCharacter): CompletableFuture<Int> {
        return CompletableFuture.supplyAsync {
            Services[RPKStatAttributeService::class.java]
                ?.statAttributes
                ?.map { statAttribute -> getStatPoints(character, statAttribute) }
                ?.also { CompletableFuture.allOf(*it.toTypedArray()).join() }
                ?.sumOf(CompletableFuture<Int>::join)
                ?: 0
        }
    }


}