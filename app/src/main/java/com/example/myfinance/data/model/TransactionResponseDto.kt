package com.example.myfinance.data.model

import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.ui.common.uiDateTimeFormat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

/**
 * Модель для получения информации о транзакции с сервера
 */

@Serializable
data class TransactionResponseDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("account")
    val account: AccountResponseDto = AccountResponseDto(),
    @SerialName("category")
    val category: CategoryResponseDto = CategoryResponseDto(),
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
) {
    fun toDomain() = Transaction(
        id = id,
        accountId = account.id,
        category = category.toDomain(),
        amount = amount.toDouble(),
        currency = account.currency,
        date = OffsetDateTime.parse(transactionDate).format(uiDateTimeFormat),
        comment = if (comment == "") null else comment
    )
}
