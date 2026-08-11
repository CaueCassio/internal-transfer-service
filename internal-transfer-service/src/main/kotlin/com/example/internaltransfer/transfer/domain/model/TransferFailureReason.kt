package com.example.internaltransfer.transfer.domain.model

enum class TransferFailureReason {
    SOURCE_ACCOUNT_NOT_FOUND,
    DESTINATION_ACCOUNT_NOT_FOUND,
    INSUFFICIENT_BALANCE,
    DESTINATION_ACCOUNT_INACTIVE,
    INVALID_AMOUNT,
    UNSUPPORTED_CURRENCY,
    SAME_ACCOUNT
}