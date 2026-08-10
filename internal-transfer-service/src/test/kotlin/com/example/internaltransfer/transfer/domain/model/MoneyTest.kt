package com.example.internaltransfer.transfer.domain.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class MoneyTest {

    @Test
    fun `deve criar valor monetario com valor e moeda informados`(){

        val money = Money.of(
            BigDecimal("150.75"),
            Currency.BRL
        )
        assertEquals(BigDecimal("150.75"), money.amount)
        assertEquals(Currency.BRL, money.currency)
    }

    @Test
    fun `deve normalizar valor inteiro para duas casas decimais`() {
        val money = Money.of(
            BigDecimal("150"),
            Currency.BRL
        )
        assertEquals(BigDecimal("150.00"), money.amount)
    }

    @Test
    fun `nao deve aceitar valor com mais de duas casas decimais`() {
        assertThrows<ArithmeticException> {
            Money.of(
                BigDecimal("150.755"),
                Currency.BRL
            )
        }
    }


    @Test
    fun `deve somar valores da mesma modeda`(){
        val money = Money.of(BigDecimal("100.00"), Currency.BRL)
        val other = Money.of(BigDecimal("50.00"), Currency.BRL)

        val result = money + other

        assertEquals(BigDecimal("150.00"), result.amount)
    }


    @Test
    fun `deve subtrair valores da mesma moeda`() {
        val money = Money.of(BigDecimal("100.00"), Currency.BRL)
        val other = Money.of(BigDecimal("40.00"), Currency.BRL)

        val result = money - other

        assertEquals(BigDecimal("60.00"), result.amount)
    }

    @Test
    fun `deve identificar quando valor for maior ou igual`() {
        val money = Money.of(BigDecimal("100.00"), Currency.BRL)
        val other = Money.of(BigDecimal("80.00"), Currency.BRL)

        assertTrue(money.isGreaterThanOrEqualTo(other))
    }

    @Test
    fun `deve retornar true quando valor for positivo`() {
        val money = Money.of(BigDecimal("10.00"), Currency.BRL)

        assertTrue(money.isPositive())
    }

    @Test
    fun `deve retornar false quando valor for zero`() {
        val money = Money.of(BigDecimal("0.00"), Currency.BRL)

        assertFalse(money.isPositive())
    }

    @Test
    fun `deve retornar false quando valor for negativo`() {
        val money = Money.of(BigDecimal("-10.00"), Currency.BRL)

        assertFalse(money.isPositive())
    }

    @Test
    fun `deve retornar true quando valor for maior`() {
        val money = Money.of(BigDecimal("100.00"), Currency.BRL)
        val other = Money.of(BigDecimal("50.00"), Currency.BRL)

        assertTrue(money.isGreaterThanOrEqualTo(other))
    }

    @Test
    fun `deve retornar true quando valores forem iguais`() {
        val money = Money.of(BigDecimal("100.00"), Currency.BRL)
        val other = Money.of(BigDecimal("100.00"), Currency.BRL)

        assertTrue(money.isGreaterThanOrEqualTo(other))
    }

    @Test
    fun `deve retornar false quando valor for menor`() {
        val money = Money.of(BigDecimal("50.00"), Currency.BRL)
        val other = Money.of(BigDecimal("100.00"), Currency.BRL)

        assertFalse(money.isGreaterThanOrEqualTo(other))
    }

}