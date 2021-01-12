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

package com.rpkit.characters.bukkit.web.race

import com.rpkit.characters.bukkit.race.RPKRace
import org.http4k.core.Body
import org.http4k.format.Gson.auto

data class RaceResponse(
    val id: Int,
    val name: String
) {
    companion object {
        val lens = Body.auto<RaceResponse>().toLens()
        val listLens = Body.auto<List<RaceResponse>>().toLens()
    }
}

fun RPKRace.toRaceResponse() = RaceResponse(
    id?.value ?: 0,
    name
)