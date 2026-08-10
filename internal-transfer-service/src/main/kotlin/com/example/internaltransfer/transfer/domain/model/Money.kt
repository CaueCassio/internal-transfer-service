package com.example.internaltransfer.transfer.domain.model

import java.math.BigDecimal
import java.math.RoundingMode

data class Money private constructor(
    val amount: BigDecimal,
    val currency: Currency
) {

    operator fun plus(other: Money): Money {
        validateSameCurrency(other)
        return of(amount.add(other.amount), currency)

    }

    operator fun minus(other: Money): Money {
        validateSameCurrency(other)
        return of(amount.subtract(other.amount), currency)
    }

    fun isPositive(): Boolean = amount > BigDecimal.ZERO

    fun isGreaterThanOrEqualTo(other: Money): Boolean {
        validateSameCurrency(other)
        return amount >= other.amount
    }

    private fun validateSameCurrency(other: Money) {
        require(currency == other.currency){
            "A moedas devem ser iguais"
        }
    }

    companion object {
        fun of(amount: BigDecimal, currency: Currency): Money {
            val normalizedAmount = amount.setScale(2, RoundingMode.UNNECESSARY)
            return Money(
                amount = normalizedAmount,
                currency = currency
           )
        }
    }
}
