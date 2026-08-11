package com.example.internaltransfer.transfer.adapter.output.dynamodb.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.time.Clock

@Configuration
@EnableConfigurationProperties(DynamoDbProperties::class)
class DynamoDbConfig {

    @Bean
    fun dynamoDbClient(
        properties: DynamoDbProperties
    ): DynamoDbClient {
        val builder = DynamoDbClient.builder()
            .region(Region.of(properties.region))

        properties.endpoint?.let {
            builder.endpointOverride(it)
        }

        return builder.build()
    }

    @Bean
    fun dynamoDbEnhancedClient(
        dynamoDbClient: DynamoDbClient
    ): DynamoDbEnhancedClient =
        DynamoDbEnhancedClient.builder()
            .dynamoDbClient(dynamoDbClient)
            .build()


    @Bean
    fun clock(): Clock =
        Clock.systemUTC()
}