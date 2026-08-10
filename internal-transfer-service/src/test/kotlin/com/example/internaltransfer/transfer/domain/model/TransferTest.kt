package com.example.internaltransfer.transfer.domain.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

class TransferTest {

    @Test
    fun `deve retornar true quando conta de origem e destino forem iguais`(){
        val transfer = Transfer(
            transfer = UUID.randomUUID(),
            sourceAccountId = "caue-account-1",
            destinationAccountId = "caue-account-1",
            amount = Money.of(BigDecimal("100.00"), Currency.BRL),
            requestedAt = Instant.now(),
        )
        assertTrue(transfer.isSameAccount())
    }

    @Test
    fun `deve retornar false quando conta de origem e destino forem diferentes`(){
        val transfer = Transfer(
            transfer = UUID.randomUUID(),
            sourceAccountId = "caue-account-1",
            destinationAccountId = "caue-account-2",
            amount = Money.of(BigDecimal("100.00"), Currency.BRL),
            requestedAt = Instant.now(),
        )
        assertFalse(transfer.isSameAccount())
    }

}