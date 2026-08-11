package com.example.internaltransfer.transfer.domain.exception

import com.example.internaltransfer.transfer.domain.model.TransferFailureReason

class InvalidTransferAmountException :
    TransferBusinessException(
        reason = TransferFailureReason.INVALID_AMOUNT,
        message = "O valor da transferência deve ser maior que zero"
    )