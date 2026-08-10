package com.example.internaltransfer.transfer.domain.model

import java.time.Instant
import java.util.UUID

data class Transfer(
    val transfer: UUID,
    val sourceAccountId: String,
    val destinationAccountId: String,
    val amount: Money,
    val requestedAt: Instant
) {

    fun isSameAccount(): Boolean =
        sourceAccountId == destinationAccountId

}
