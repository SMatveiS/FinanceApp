package com.example.model

/**
 * Доменная модель транзакций
 */

data class Transaction(
    val id: Int,
    val accountId: Int,
    val category: Category,
    val amount: Double,
    val date: String,
    val comment: String? = null
)
