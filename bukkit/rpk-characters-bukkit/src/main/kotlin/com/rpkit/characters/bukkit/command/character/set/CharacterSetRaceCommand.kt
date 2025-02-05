/*
 * Copyright 2022 Ren Binden
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

package com.rpkit.characters.bukkit.command.character.set

import com.rpkit.characters.bukkit.RPKCharactersBukkit
import com.rpkit.characters.bukkit.character.RPKCharacterService
import com.rpkit.characters.bukkit.race.RPKRaceName
import com.rpkit.characters.bukkit.race.RPKRaceService
import com.rpkit.core.service.Services
import com.rpkit.players.bukkit.profile.minecraft.RPKMinecraftProfileService
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.conversations.*
import org.bukkit.entity.Player

/**
 * Character set race command.
 * Sets character's race.
 */
class CharacterSetRaceCommand(private val plugin: RPKCharactersBukkit) : CommandExecutor {
    private val conversationFactory: ConversationFactory

    init {
        conversationFactory = ConversationFactory(plugin)
                .withModality(true)
                .withFirstPrompt(RacePrompt())
                .withEscapeSequence("cancel")
                .thatExcludesNonPlayersWithMessage(plugin.messages.notFromConsole)
                .addConversationAbandonedListener { event ->
                    if (!event.gracefulExit()) {
                        val conversable = event.context.forWhom
                        if (conversable is Player) {
                            conversable.sendMessage(plugin.messages.operationCancelled)
                        }
                    }
                }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(plugin.messages.notFromConsole)
            return true
        }
        if (!sender.hasPermission("rpkit.characters.command.character.set.race")) {
            sender.sendMessage(plugin.messages.noPermissionCharacterSetRace)
            return true
        }
        val minecraftProfileService = Services[RPKMinecraftProfileService::class.java]
        if (minecraftProfileService == null) {
            sender.sendMessage(plugin.messages.noMinecraftProfileService)
            return true
        }
        val characterService = Services[RPKCharacterService::class.java]
        if (characterService == null) {
            sender.sendMessage(plugin.messages.noCharacterService)
            return true
        }
        val minecraftProfile = minecraftProfileService.getPreloadedMinecraftProfile(sender)
        if (minecraftProfile == null) {
            sender.sendMessage(plugin.messages.noMinecraftProfile)
            return true
        }
        val character = characterService.getPreloadedActiveCharacter(minecraftProfile)
        if (character == null) {
            sender.sendMessage(plugin.messages.noCharacter)
            return true
        }
        if (args.isEmpty()) {
            conversationFactory.buildConversation(sender).begin()
            return true
        }
        val raceBuilder = StringBuilder()
        for (i in 0 until args.size - 1) {
            raceBuilder.append(args[i]).append(" ")
        }
        raceBuilder.append(args[args.size - 1])
        val raceService = Services[RPKRaceService::class.java]
        if (raceService == null) {
            sender.sendMessage(plugin.messages.noRaceService)
            return true
        }
        val race = raceService.getRace(RPKRaceName(raceBuilder.toString()))
        if (race == null) {
            sender.sendMessage(plugin.messages.characterSetRaceInvalidRace)
            return true
        }
        character.race = race
        characterService.updateCharacter(character).thenAccept { updatedCharacter ->
            sender.sendMessage(plugin.messages.characterSetRaceValid)
            updatedCharacter?.showCharacterCard(minecraftProfile)
        }
        return true
    }

    private inner class RacePrompt : ValidatingPrompt() {

        override fun isInputValid(context: ConversationContext, input: String): Boolean {
            return Services[RPKRaceService::class.java]?.getRace(RPKRaceName(input)) != null
        }

        override fun acceptValidatedInput(context: ConversationContext, input: String): Prompt {
            val conversable = context.forWhom
            if (conversable !is Player) return RaceSetPrompt()
            val minecraftProfileService = Services[RPKMinecraftProfileService::class.java] ?: return RaceSetPrompt()
            val characterService = Services[RPKCharacterService::class.java] ?: return RaceSetPrompt()
            val raceService = Services[RPKRaceService::class.java] ?: return RaceSetPrompt()
            val minecraftProfile = minecraftProfileService.getPreloadedMinecraftProfile(conversable) ?: return RaceSetPrompt()
            val character = characterService.getPreloadedActiveCharacter(minecraftProfile) ?: return RaceSetPrompt()
            character.race = raceService.getRace(RPKRaceName(input))!!
            characterService.updateCharacter(character)
            return RaceSetPrompt()
        }

        override fun getFailedValidationText(context: ConversationContext, invalidInput: String): String {
            return plugin.messages.characterSetRaceInvalidRace
        }

        override fun getPromptText(context: ConversationContext): String {
            val raceService = Services[RPKRaceService::class.java] ?: return plugin.messages.noRaceService
            val raceListBuilder = StringBuilder()
            for (race in raceService.races) {
                raceListBuilder.append(plugin.messages.raceListItem.withParameters(race)).append('\n')
            }
            return plugin.messages.characterSetRacePrompt + "\n" + raceListBuilder.toString()
        }

    }

    private inner class RaceSetPrompt : MessagePrompt() {

        override fun getNextPrompt(context: ConversationContext): Prompt? {
            val conversable = context.forWhom
            if (conversable is Player) {
                val minecraftProfileService = Services[RPKMinecraftProfileService::class.java]
                if (minecraftProfileService == null) {
                    conversable.sendMessage(plugin.messages.noMinecraftProfileService)
                    return END_OF_CONVERSATION
                }
                val characterService = Services[RPKCharacterService::class.java]
                if (characterService == null) {
                    conversable.sendMessage(plugin.messages.noCharacterService)
                    return END_OF_CONVERSATION
                }
                val raceService = Services[RPKRaceService::class.java]
                if (raceService == null) {
                    conversable.sendMessage(plugin.messages.noRaceService)
                    return END_OF_CONVERSATION
                }
                val minecraftProfile = minecraftProfileService.getPreloadedMinecraftProfile(context.forWhom as Player)
                if (minecraftProfile != null) {
                    characterService.getPreloadedActiveCharacter(minecraftProfile)?.showCharacterCard(minecraftProfile)
                }
            }
            return END_OF_CONVERSATION
        }

        override fun getPromptText(context: ConversationContext): String {
            return plugin.messages.characterSetRaceValid
        }

    }

}
