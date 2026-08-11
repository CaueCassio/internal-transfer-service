package com.example.internaltransfer.transfer.adapter.output.dynamodb.item

import com.example.internaltransfer.transfer.domain.model.Transfer
import com.example.internaltransfer.transfer.domain.model.TransferStatus
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import java.math.BigDecimal
import java.time.Instant


@DynamoDbBean
data class TransactionItem(

    @get:DynamoDbPartitionKey
    var transferId: String? = null,
    var sourceAccountId: String? = null,
    var destinationAccountId: String? = null,
    var amount: BigDecimal? = null,
    var currency: String? = null,
    var requestedAt: Instant? = null,
    var processedAt: Instant? = null,

    var status: String? = null
) {

    companion object {

        fun completed(
            transfer: Transfer,
            processedAt: Instant
        ): TransactionItem =
            TransactionItem(
                transferId = transfer.transfer.toString(),
                sourceAccountId = transfer.sourceAccountId,
                destinationAccountId = transfer.destinationAccountId,
                amount = transfer.amount.amount,
                currency = transfer.amount.currency.name,
                requestedAt = transfer.requestedAt,
                processedAt = processedAt,
                status = TransferStatus.COMPLETED.name
           )
      }
 }
