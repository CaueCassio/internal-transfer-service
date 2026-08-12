package com.example.internaltransfer.transfer.adapter.input.kafka

import com.example.internaltransfer.transfer.adapter.input.kafka.event.TransferRequestedEvent
import com.example.internaltransfer.transfer.adapter.input.mapper.toDomain
import com.example.internaltransfer.transfer.application.port.input.ProcessTransferUseCase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class TransferRequestedConsumerComponent(
    private val processTransferUseCase: ProcessTransferUseCase
) {

    @KafkaListener(
        topics = ["\${app.kafka.topics.transfer-requested}"],
        groupId = "internal-transfer-service",
        containerFactory = "transferRequestedKafkaListenerContainerFactory",
    )

    fun consume(event: TransferRequestedEvent){
        print("Transferencia recebida $event" )
//        processTransferUseCase.execute(
//            event.toDomain()
//        )
    }
}
