package com.example.internaltransfer.transfer.adapter.input.kafka.event

import java.math.BigDecimal
import java.time.Instant

data class TransferRequestedEvent(
    val transferId: String,
    val sourceAccountId: String,
    val destinationAccountId: String,
    val amount: BigDecimal,
    val currency: String,
    val requestedAt: Instant

)
