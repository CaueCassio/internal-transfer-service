package com.example.internaltransfer.transfer.application.port.input

import com.example.internaltransfer.transfer.domain.model.Transfer

interface ProcessTransferUseCase {

    fun execute(transfer: Transfer)
}