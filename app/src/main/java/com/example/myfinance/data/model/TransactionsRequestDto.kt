package com.example.myfinance.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TransactionRequestDto(
    @SerialName("accountId")
    val accountId: Int = 1,
    @SerialName("categoryId")
    val categoryId: Int = 1,
    @SerialName("amount")
    val amount: String = "500.00",
    @SerialName("transactionDate")
    val transactionDate: String = "2025-07-11T21:45:34.430Z",
    @SerialName("comment")
    val comment: String? = null
)