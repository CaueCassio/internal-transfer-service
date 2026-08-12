package com.example.internaltransfer.transfer.adapter.output.dynamodb.exception

class ConcurrentTransferException(
    cause: Throwable
) : RuntimeException(
    "Conflito concorrente durante o processamento da transferência",
    cause
)