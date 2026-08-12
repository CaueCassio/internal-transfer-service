package com.example.internaltransfer.transfer.application.config

import com.example.internaltransfer.transfer.application.port.input.ProcessTransferUseCase
import com.example.internaltransfer.transfer.application.port.output.AccountRepository
import com.example.internaltransfer.transfer.application.port.output.TransferPersistencePort
import com.example.internaltransfer.transfer.application.service.ProcessTransferService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TransferApplicationConfigConfiguration {

    @Bean
    fun processTransferUseCase(
        accountRepository: AccountRepository,
        transferPersistencePort: TransferPersistencePort
    ): ProcessTransferUseCase =
        ProcessTransferService(
            accountRepository = accountRepository,
            transferPersistencePort = transferPersistencePort
        )
}
