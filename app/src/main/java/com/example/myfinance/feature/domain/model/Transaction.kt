package com.example.myfinance.feature.domain.model

import com.example.myfinance.data.model.CategoryDto

/**
 * Доменная модель транзакций
 */

data class Transaction(
    val id: Int,
    val accountId: Int,
    val category: CategoryDto,
    val amount: Double,
    val date: String,
    val comment: String? = null
)
