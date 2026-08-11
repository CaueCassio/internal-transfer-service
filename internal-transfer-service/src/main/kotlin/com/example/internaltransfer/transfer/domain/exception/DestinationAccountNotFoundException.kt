package com.example.internaltransfer.transfer.domain.exception

import com.example.internaltransfer.transfer.domain.model.TransferFailureReason

class DestinationAccountNotFoundException :
    TransferBusinessException(
        reason = TransferFailureReason.DESTINATION_ACCOUNT_NOT_FOUND,
        message = "Conta de destino não encontrada"
    )