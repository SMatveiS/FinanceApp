package com.example.myfinance.domain.model

import com.example.myfinance.data.model.AccountRequestDto
import kotlinx.serialization.Serializable

/**
 * Доменная модель аккаунта
 */

@Serializable
data class Account(
    val id: Int,
    val name: String,
    val balance: Double,
    val currency: String
) {

    fun toDto() = AccountRequestDto(
        name = name,
        balance = balance.toString(),
        currency = currency
    )
}
