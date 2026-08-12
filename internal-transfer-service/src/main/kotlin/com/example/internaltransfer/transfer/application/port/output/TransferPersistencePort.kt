package com.example.internaltransfer.transfer.application.port.output

import com.example.internaltransfer.transfer.domain.model.Account
import com.example.internaltransfer.transfer.domain.model.Transfer
import com.example.internaltransfer.transfer.domain.model.TransferFailureReason

interface TransferPersistencePort {
    fun executeAtomically(
        transfer: Transfer,
        sourceAccount: Account,
        destinationAccount: Account,
        )

    fun registerFailure(
        transfer: Transfer,
        reason: TransferFailureReason
    )
}