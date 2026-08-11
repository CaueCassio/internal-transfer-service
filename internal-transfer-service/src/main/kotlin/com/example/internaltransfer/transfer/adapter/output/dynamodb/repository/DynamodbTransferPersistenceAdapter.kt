package com.example.internaltransfer.transfer.adapter.output.dynamodb.repository

import com.example.internaltransfer.transfer.adapter.output.dynamodb.config.DynamoDbProperties
import com.example.internaltransfer.transfer.adapter.output.dynamodb.item.TransactionItem
import com.example.internaltransfer.transfer.application.port.output.TransferPersistencePort
import com.example.internaltransfer.transfer.domain.model.Account
import com.example.internaltransfer.transfer.domain.model.AccountStatus
import com.example.internaltransfer.transfer.domain.model.Transfer
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.Put
import software.amazon.awssdk.services.dynamodb.model.TransactWriteItem
import software.amazon.awssdk.services.dynamodb.model.TransactWriteItemsRequest
import software.amazon.awssdk.services.dynamodb.model.Update


import java.time.Clock
import java.time.Instant

@Repository
class DynamodbTransferPersistenceAdapter(
    private val dynamoDbClient : DynamoDbClient,
    private val properties: DynamoDbProperties,
    private val clock: Clock
): TransferPersistencePort {

    private val transactionSchema =
        TableSchema.fromBean(TransactionItem::class.java)

    override fun executeAtomically(
        transfer: Transfer,
        sourceAccount: Account,
        destinationAccount: Account
    ) {
        val amount = AttributeValue.fromN(
            transfer.amount.amount.toPlainString()
        )

        val currency = AttributeValue.fromS(
            transfer.amount.currency.name
        )

        val sourceUpdate = Update.builder()
            .tableName(properties.accountsTable)
            .key(
                mapOf(
                    "accountId" to AttributeValue.fromS(sourceAccount.accountId)
                )
            )
            .updateExpression(
                "SET #balance = #balance - :amount"
            )
            .conditionExpression(
                """
                attribute_exists(#accountId)
                AND #balance >= :amount
                AND #currency = :currency
                """.trimIndent().replace("\n", " ")
            )
            .expressionAttributeNames(
                mapOf(
                    "#accountId" to "accountId",
                    "#balance" to "balance",
                    "#currency" to "currency"
                )
            )
            .expressionAttributeValues(
                mapOf(
                    ":amount" to amount,
                    ":currency" to currency
                )
            )
            .build()

        val destinationUpdate = Update.builder()
            .tableName(properties.accountsTable)
            .key(
                mapOf(
                    "accountId" to AttributeValue.fromS(destinationAccount.accountId)
                )
            )
            .updateExpression(
                "SET #balance = #balance + :amount"
            )
            .conditionExpression(
                """
                attribute_exists(#accountId)
                AND #status = :active
                AND #currency = :currency
                """.trimIndent().replace("\n", " ")
            )
            .expressionAttributeNames(
                mapOf(
                    "#accountId" to "accountId",
                    "#balance" to "balance",
                    "#status" to "status",
                    "#currency" to "currency"
                )
            )
            .expressionAttributeValues(
                mapOf(
                    ":amount" to amount,
                    ":active" to AttributeValue.fromS(AccountStatus.ACTIVE.name),
                    ":currency" to currency
                )
            )
            .build()

        val transactionItem = TransactionItem.completed(
            transfer = transfer,
            processedAt = Instant.now(clock)
        )

        val transactionPut = Put.builder()
            .tableName(properties.transactionsTable)
            .item(
                transactionSchema.itemToMap(
                    transactionItem,
                    true
                )
            )
            .conditionExpression(
                "attribute_not_exists(#transferId)"
            )
            .expressionAttributeNames(
                mapOf(
                    "#transferId" to "transferId"
                )
            )
            .build()

        val request = TransactWriteItemsRequest.builder()
            .transactItems(
                TransactWriteItem.builder()
                    .update(sourceUpdate)
                    .build(),

                TransactWriteItem.builder()
                    .update(destinationUpdate)
                    .build(),

                TransactWriteItem.builder()
                    .put(transactionPut)
                    .build()
            )
            .clientRequestToken(
                transfer.transferId.toString()
            )
            .build()

        dynamoDbClient.transactWriteItems(request)

    }
}