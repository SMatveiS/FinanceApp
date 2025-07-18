package com.example.myfinance.data.model

import com.example.myfinance.domain.model.Account
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Модель для получения информации об аккаунте с сервера
 */

@Serializable
data class AccountResponseDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("userId")
    val userId: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("balance")
    val balance: String = "",
    @SerialName("currency")
    val currency: String = "",
    @SerialName("createdAt")
    val createdAt: String = "",
    @SerialName("updatedAt")
    val updatedAt: String = ""
) {

    fun toDomain() = Account(
        id = id,
        name = name,
        balance = balance.toDouble(),
        currency = currency,
        createdAt = createdAt
    )
}
