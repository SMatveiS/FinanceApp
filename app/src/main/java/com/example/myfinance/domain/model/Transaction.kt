package com.example.myfinance.domain.model

import com.example.myfinance.data.model.TransactionRequestDto
import com.example.myfinance.domain.utils.formatUiDateToDtoDate

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
) {

    fun toDto() = TransactionRequestDto(
        accountId = accountId,
        categoryId = category.id,
        amount = amount.toString(),
        transactionDate = formatUiDateToDtoDate(date),
        comment = comment
    )
}
