package com.example.internaltransfer.transfer.domain.exception

import com.example.internaltransfer.transfer.domain.model.TransferFailureReason

class SourceAccountNotFoundException :
    TransferBusinessException(
        reason = TransferFailureReason.SOURCE_ACCOUNT_NOT_FOUND,
        message = "Conta de origem não encontrada"
    )