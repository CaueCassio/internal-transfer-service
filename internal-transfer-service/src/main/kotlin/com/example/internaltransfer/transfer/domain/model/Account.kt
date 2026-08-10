package com.example.internaltransfer.transfer.domain.model

data class Account(
    val accountId: String,
    val balance: Money,
    val status: AccountStatus
)
{
    fun isActive(): Boolean =
        status == AccountStatus.ACTIVE

    fun hasSufficientBalance(amount: Money): Boolean =
        balance.isGreaterThanOrEqualTo(amount)
}
