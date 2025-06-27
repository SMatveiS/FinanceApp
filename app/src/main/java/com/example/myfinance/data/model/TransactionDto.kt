package com.example.myfinance.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Модель для получения информации о транзакции с сервера
 */

@Serializable
data class TransactionDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("account")
    val account: AccountDto = AccountDto(),
    @SerialName("category")
    val category: CategoryDto = CategoryDto(),
    @SerialName("amount")
    val amount: String = "",
    @SerialName("transactionDate")
    val transactionDate: String = "",
    @SerialName("comment")
    val comment: String? = null,
    @SerialName("createdAt")
    val createdAt: String = "",
    @SerialName("updatedAt")
    val updatedAt: String = ""
)
