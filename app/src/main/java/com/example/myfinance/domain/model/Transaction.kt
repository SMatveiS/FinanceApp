package com.example.myfinance.domain.model

import com.example.myfinance.data.model.CategoryDto
import com.example.myfinance.data.model.TransactionDto

/**
 * Доменная модель транзакций
 */

data class Transaction(
    val id: Int,
    val accountId: Int,
    val category: CategoryDto,
    val amount: Double,
    val currency: String,
    val date: String,
    val comment: String? = null
) {

    fun toDto() = TransactionDto(
        id = id,
        category = category,
        amount = amount.toString(),
        transactionDate = date,
        comment = comment
    )
}
