package com.example.internaltransfer.transfer.adapter.input.kafka.config

import com.example.internaltransfer.transfer.adapter.input.kafka.event.TransferRequestedEvent
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class KafkaConsumerConfig(
    private val kafkaProperties: KafkaProperties
) {

    @Bean
    fun transferRequestedConsumerFactory():
            ConsumerFactory<String, TransferRequestedEvent> {

        val jsonDeserializer =
            JsonDeserializer(
                TransferRequestedEvent::class.java,
                false
            )

        return DefaultKafkaConsumerFactory(
            kafkaProperties.buildConsumerProperties(),
            StringDeserializer(),
            jsonDeserializer
        )
    }

    @Bean
    fun transferRequestedKafkaListenerContainerFactory(
        transferRequestedConsumerFactory:
        ConsumerFactory<String, TransferRequestedEvent>
    ): ConcurrentKafkaListenerContainerFactory<String, TransferRequestedEvent> {

        return ConcurrentKafkaListenerContainerFactory<String, TransferRequestedEvent>().apply {
            consumerFactory = transferRequestedConsumerFactory
        }
    }
}