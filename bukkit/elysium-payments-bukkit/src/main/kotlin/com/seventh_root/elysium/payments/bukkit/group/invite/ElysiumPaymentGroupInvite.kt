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

package com.seventh_root.elysium.payments.bukkit.group.invite

import com.seventh_root.elysium.characters.bukkit.character.ElysiumCharacter
import com.seventh_root.elysium.core.database.Entity
import com.seventh_root.elysium.payments.bukkit.group.ElysiumPaymentGroup

/**
 * Represents a payment group invite.
 *
 * @property paymentGroup The payment group
 * @property character The character
 */
class ElysiumPaymentGroupInvite(
        override var id: Int = 0,
        val paymentGroup: ElysiumPaymentGroup,
        val character: ElysiumCharacter
): Entity