package com.example.myfinance.domain.model

import com.example.myfinance.data.model.TransactionRequestDto
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * Доменная модель транзакций
 */

data class Transaction(
    val id: Int,
    val accountId: Int,
    val category: Category,
    val amount: Double,
    val currency: String,
    val date: String,
    val comment: String? = null
) {

    private fun uiDateToDtoDate(date: String): String {
        val localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"))

        val zonedDateTime = localDateTime.atZone(ZoneOffset.UTC)

        return zonedDateTime.format(DateTimeFormatter.ISO_INSTANT)
    }

    fun toDto() = TransactionRequestDto(
        accountId = accountId,
        categoryId = category.id,
        amount = amount.toString(),
        transactionDate = uiDateToDtoDate(date),
        comment = comment
    )
}
