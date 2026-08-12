package com.example.internaltransfer.transfer.adapter.input.mapper

import com.example.internaltransfer.transfer.adapter.input.kafka.event.TransferRequestedEvent
import com.example.internaltransfer.transfer.domain.model.Currency
import com.example.internaltransfer.transfer.domain.model.Money
import com.example.internaltransfer.transfer.domain.model.Transfer
import java.util.UUID

fun TransferRequestedEvent.toDomain(): Transfer =
    Transfer(
        transferId = UUID.fromString(transferId),
        sourceAccountId = sourceAccountId,
        destinationAccountId = destinationAccountId,
        amount  = Money.of(
            amount = amount,
            currency = Currency.valueOf(currency)
        ),
        requestedAt = requestedAt

    )