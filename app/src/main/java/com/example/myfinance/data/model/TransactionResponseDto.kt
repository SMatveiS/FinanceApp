package com.example.myfinance.data.model

import com.example.myfinance.data.local.database.TransactionEntity
import com.example.myfinance.data.utils.formatDtoDateToUiDate
import com.example.myfinance.domain.model.Transaction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
        date = formatDtoDateToUiDate(transactionDate),
        comment = if (comment == "") null else comment
    )

    fun toEntity() = TransactionEntity(
        id = id,
        accountId = account.id,
        categoryId = category.id,
        amount = amount,
        transactionDate = transactionDate,
        comment = if (comment == "") null else comment,
        updatedAt = updatedAt
    )
}
