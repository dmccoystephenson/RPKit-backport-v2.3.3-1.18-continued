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

package com.seventh_root.elysium.economy.bukkit.command.money

import com.seventh_root.elysium.economy.bukkit.ElysiumEconomyBukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class MoneyCommand(val plugin: ElysiumEconomyBukkit): CommandExecutor {

    private val moneySubtractCommand = MoneySubtractCommand(plugin)
    private val moneyAddCommand = MoneyAddCommand(plugin)
    private val moneySetCommand = MoneySetCommand(plugin)
    private val moneyViewCommand = MoneyViewCommand(plugin)
    private val moneyPayCommand = MoneyPayCommand(plugin)
    private val moneyWalletCommand = MoneyWalletCommand(plugin)

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size > 0) {
            val newArgs = args.drop(1).toTypedArray()
            if (args[0].equals("subtract", ignoreCase = true) || args[0].equals("sub", ignoreCase = true)) {
                return moneySubtractCommand.onCommand(sender, command, label, newArgs)
            } else if (args[0].equals("add", ignoreCase = true)) {
                return moneyAddCommand.onCommand(sender, command, label, newArgs)
            } else if (args[0].equals("set", ignoreCase = true)) {
                return moneySetCommand.onCommand(sender, command, label, newArgs)
            } else if (args[0].equals("view", ignoreCase = true)) {
                return moneyViewCommand.onCommand(sender, command, label, newArgs)
            } else if (args[0].equals("pay", ignoreCase = true)) {
                return moneyPayCommand.onCommand(sender, command, label, newArgs)
            } else if (args[0].equals("wallet", ignoreCase = true)) {
                return moneyWalletCommand.onCommand(sender, command, label, newArgs)
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("messages.money-usage")))
            }
        } else {
            return moneyViewCommand.onCommand(sender, command, label, arrayOf<String>())
        }
        return true
    }

}