package com.example.internaltransfer.transfer.domain.exception

import com.example.internaltransfer.transfer.domain.model.TransferFailureReason

class UnsupportedCurrencyException :
    TransferBusinessException(
        reason = TransferFailureReason.UNSUPPORTED_CURRENCY,
        message = "A moeda da transferência deve ser BRL"
    )