package com.example.myfinance.data.model

import com.example.myfinance.domain.model.Transaction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * Модель для получения информации о транзакции с сервера
 */

@Serializable
data class TransactionDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("account")
    val account: AccountDto = AccountDto(),
    @SerialName("category")
    val category: CategoryDto = CategoryDto(),
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
        category = category,
        amount = amount.toDouble(),
        currency = account.currency,
        date = OffsetDateTime.parse(transactionDate)
            .format(DateTimeFormatter.ofPattern("dd.MM.yy HH:mm")),
        comment = if (comment == "") null else comment
    )
}
