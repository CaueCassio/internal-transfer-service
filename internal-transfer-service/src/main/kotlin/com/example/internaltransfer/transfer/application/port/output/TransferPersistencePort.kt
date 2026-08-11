package com.example.internaltransfer.transfer.application.port.output

import com.example.internaltransfer.transfer.domain.model.Account
import com.example.internaltransfer.transfer.domain.model.Transfer

interface TransferPersistencePort {
    fun executeAtomically(
        transfer: Transfer,
        sourceAccount: Account,
        destinationAccount: Account,
        )
}