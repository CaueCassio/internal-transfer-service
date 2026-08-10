package com.example.internaltransfer.transfer.domain.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class AccountTest {

    @Test
    fun `deve retornar true quando a conta estiver ativa`(){
        val account = Account(
            accountId = "caue-account-1",
            balance = Money.of(BigDecimal("100.00"), Currency.BRL),
            status = AccountStatus.ACTIVE
        )

        assertTrue(account.isActive())
    }

    @Test
    fun `deve retornar false quando a conta estiver inativa`(){
        val account = Account(
            accountId = "caue-account-1",
            balance = Money.of(BigDecimal("100.00"), Currency.BRL),
            status = AccountStatus.INACTIVE
        )
        assertFalse(account.isActive())
    }

    @Test
    fun `deve retornar true quando saldo for maior que valor solicitado`() {
        val account = Account(
            accountId = "caue-account-1",
            balance = Money.of(BigDecimal("100.00"), Currency.BRL),
            status = AccountStatus.ACTIVE
        )

        val amount = Money.of(BigDecimal("50.00"), Currency.BRL)

        assertTrue(account.hasSufficientBalance(amount))
    }

    @Test
    fun `deve retornar true quando saldo for igual ao valor solicitado`() {
        val account = Account(
            accountId = "account-1",
            balance = Money.of(BigDecimal("100.00"), Currency.BRL),
            status = AccountStatus.ACTIVE
        )

        val amount = Money.of(BigDecimal("100.00"), Currency.BRL)

        assertTrue(account.hasSufficientBalance(amount))
    }

    @Test
    fun `deve retornar false quando saldo for menor que valor solicitado`() {
        val account = Account(
            accountId = "account-1",
            balance = Money.of(BigDecimal("50.00"), Currency.BRL),
            status = AccountStatus.ACTIVE
        )

        val amount = Money.of(BigDecimal("100.00"), Currency.BRL)

        assertFalse(account.hasSufficientBalance(amount))
    }
}