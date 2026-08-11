package com.example.internaltransfer.transfer.adapter.output.dynamodb.repository

import com.example.internaltransfer.transfer.adapter.output.dynamodb.config.DynamoDbProperties
import com.example.internaltransfer.transfer.adapter.output.dynamodb.item.AccountItem
import com.example.internaltransfer.transfer.application.port.output.AccountRepository
import com.example.internaltransfer.transfer.domain.model.Account
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest

@Repository
class DynamoDbAccountRepository(
    dynamoDbEnhancedClient: DynamoDbEnhancedClient,
    properties : DynamoDbProperties
): AccountRepository {

    private val accountTable =
        dynamoDbEnhancedClient.table(
            properties.accountsTable,
            TableSchema.fromBean(AccountItem::class.java)
        )

    override fun findById(accountId: String): Account? {
        val key = Key.builder()
            .partitionValue(accountId)
            .build()

        val request = GetItemEnhancedRequest.builder()
            .key(key)
            .consistentRead(true)
            .build()

        return accountTable
            .getItem(request)
            ?.toDomain()
    }
}