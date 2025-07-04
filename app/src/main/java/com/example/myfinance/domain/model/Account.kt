package com.example.myfinance.domain.model

import com.example.myfinance.data.model.AccountDto

/**
 * Доменная модель аккаунта
 */

data class Account(
    val id: Int,
    val name: String,
    val balance: Double,
    val currency: String
) {

    fun toDto() = AccountDto(
        id = id,
        name = name,
        balance = balance.toString(),
        currency = currency
    )
}
