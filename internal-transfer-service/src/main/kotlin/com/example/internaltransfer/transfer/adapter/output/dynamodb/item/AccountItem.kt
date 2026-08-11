package com.example.internaltransfer.transfer.adapter.output.dynamodb.item

import com.example.internaltransfer.transfer.domain.model.Account
import com.example.internaltransfer.transfer.domain.model.AccountStatus
import com.example.internaltransfer.transfer.domain.model.Currency
import com.example.internaltransfer.transfer.domain.model.Money
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import java.math.BigDecimal

@DynamoDbBean
data class AccountItem(

    @get:DynamoDbPartitionKey
    var accountId: String? = null,
    var balance: BigDecimal? = null,
    var currency: String? = null,
    var status: String? = null
) {

    fun toDomain(): Account =
        Account(
            accountId = requireNotNull(accountId) {
                "Identificador da conta não encontrado"
            },
            balance = Money.of(
                amount = requireNotNull(balance) {
                    "Saldo da conta não encontrado"
                },
                currency = Currency.valueOf(
                    requireNotNull(currency) {
                        "Moeda da conta não encontrada"
                    }
                )
            ),
            status = AccountStatus.valueOf(
                requireNotNull(status) {
                    "Status da conta não encontrado"
                }
            )
        )

}
