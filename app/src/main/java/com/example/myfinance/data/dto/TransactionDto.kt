package com.example.myfinance.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("account")
    val account: AccountDto? = null,
    @SerialName("category")
    val category: CategoryDto? = null,
    @SerialName("amount")
    val amount: Int? = null,
    @SerialName("transactionDate")
    val transactionDate: String? = null,
    @SerialName("comment")
    val comment: String? = null,
    @SerialName("createdAt")
    val createdAt: String? = null,
    @SerialName("updatedAt")
    val updatedAt: String? = null
)