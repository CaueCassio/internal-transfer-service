package com.example.internaltransfer.transfer.adapter.output.dynamodb.config

import org.springframework.boot.context.properties.ConfigurationProperties
import java.net.URI


@ConfigurationProperties(prefix = "app.dynamodb")
data class DynamoDbProperties(
    val region: String,
    val endpoint: URI? = null,
    val accountsTable: String,
    val transactionsTable: String
)
