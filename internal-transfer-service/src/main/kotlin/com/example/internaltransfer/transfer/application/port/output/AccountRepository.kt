package com.example.internaltransfer.transfer.application.port.output

import com.example.internaltransfer.transfer.domain.model.Account

interface AccountRepository {
    fun findById(accountId: String): Account?
}