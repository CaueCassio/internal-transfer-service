package com.example.internaltransfer.transfer.application.service

import com.example.internaltransfer.transfer.application.port.input.ProcessTransferUseCase
import com.example.internaltransfer.transfer.application.port.output.AccountRepository
import com.example.internaltransfer.transfer.application.port.output.TransferPersistencePort
import com.example.internaltransfer.transfer.domain.exception.DestinationAccountInactiveException
import com.example.internaltransfer.transfer.domain.exception.InsufficientBalanceException
import com.example.internaltransfer.transfer.domain.exception.InvalidTransferAmountException
import com.example.internaltransfer.transfer.domain.exception.SourceAccountNotFoundException
import com.example.internaltransfer.transfer.domain.model.Transfer

class ProcessTransferService(
    private val accountRepository: AccountRepository,
    private val transferPersistencePort: TransferPersistencePort
): ProcessTransferUseCase {

    override fun execute(transfer: Transfer) {

        validateTransfer(transfer)

        val sourceAccount =
            accountRepository.findById(transfer.sourceAccountId)
                ?: throw SourceAccountNotFoundException()

        val destinationAccount = accountRepository.findById(transfer.destinationAccountId)
            ?: throw DestinationAccountInactiveException()

        if (!destinationAccount.isActive()){
            throw DestinationAccountInactiveException()
        }

        if(!sourceAccount.hasSufficientBalance(transfer.amount))
            throw InsufficientBalanceException()

        transferPersistencePort.executeAtomically(
            transfer = transfer,
            sourceAccount = sourceAccount,
            destinationAccount = destinationAccount
        )

    }

    private fun validateTransfer(transfer: Transfer) {
        if(!transfer.amount.isPositive()){
            throw InvalidTransferAmountException()
        }
    }
}