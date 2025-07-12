package com.example.myfinance.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionShortResponseDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("accountId")
    val accountId: Int = 0,
    @SerialName("categoryId")
    val categoryId: Int = 0,
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