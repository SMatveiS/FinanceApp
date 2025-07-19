package com.example.myfinance.domain.model

data class TransactionBrief(
    val id: Int,
    val accountId: Int,
    val categoryId: Int,
    val amount: Double,
    val date: String,
    val comment: String? = null
)