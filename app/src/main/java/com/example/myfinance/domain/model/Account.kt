package com.example.myfinance.domain.model

import com.example.myfinance.data.model.AccountResponseDto

/**
 * Доменная модель аккаунта
 */

data class Account(
    val id: Int,
    val name: String,
    val balance: Double,
    val currency: String
) {

    fun toDto() = AccountResponseDto(
        id = id,
        name = name,
        balance = balance.toString(),
        currency = currency
    )
}
