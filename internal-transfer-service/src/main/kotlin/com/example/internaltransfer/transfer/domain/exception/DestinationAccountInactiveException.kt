package com.example.internaltransfer.transfer.domain.exception

import com.example.internaltransfer.transfer.domain.model.TransferFailureReason

class DestinationAccountInactiveException :
    TransferBusinessException(
        reason = TransferFailureReason.DESTINATION_ACCOUNT_INACTIVE,
        message = "A conta de destino está inativa"
    )