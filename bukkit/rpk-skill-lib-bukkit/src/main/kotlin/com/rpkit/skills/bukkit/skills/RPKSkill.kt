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

package com.rpkit.skills.bukkit.skills

import com.rpkit.characters.bukkit.character.RPKCharacter
import java.util.concurrent.CompletableFuture


interface RPKSkill {

    val name: RPKSkillName
    val manaCost: Int
    val cooldown: Int

    fun use(character: RPKCharacter)

    fun canUse(character: RPKCharacter): CompletableFuture<Boolean>

}

fun RPKCharacter.use(skill: RPKSkill) {
    skill.use(this)
}

fun RPKCharacter.canUse(skill: RPKSkill): CompletableFuture<Boolean> {
    return skill.canUse(this)
}