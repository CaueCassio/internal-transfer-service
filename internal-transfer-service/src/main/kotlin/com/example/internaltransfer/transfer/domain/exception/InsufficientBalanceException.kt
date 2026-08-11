package com.example.internaltransfer.transfer.domain.exception

import com.example.internaltransfer.transfer.domain.model.TransferFailureReason

class InsufficientBalanceException : TransferBusinessException(
    reason = TransferFailureReason.INSUFFICIENT_BALANCE,
    message = "Saldo insuficiente para realizar a transferencia"
)