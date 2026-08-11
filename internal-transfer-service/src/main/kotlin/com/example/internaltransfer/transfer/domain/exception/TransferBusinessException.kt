package com.example.internaltransfer.transfer.domain.exception

import com.example.internaltransfer.transfer.domain.model.TransferFailureReason

sealed class TransferBusinessException (
    val reason: TransferFailureReason,
    message: String
): RuntimeException(message)