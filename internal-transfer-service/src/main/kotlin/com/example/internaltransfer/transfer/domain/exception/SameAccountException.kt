package com.example.internaltransfer.transfer.domain.exception

import com.example.internaltransfer.transfer.domain.model.TransferFailureReason

class SameAccountException :
    TransferBusinessException(
        reason = TransferFailureReason.SAME_ACCOUNT,
        message = "As contas de origem e destino devem ser diferentes"
    )