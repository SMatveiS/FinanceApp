package com.example.myfinance.data.model

import com.example.myfinance.data.local.database.TransactionEntity
import com.example.myfinance.data.utils.formatDtoDateToUiDate
import com.example.myfinance.domain.model.TransactionBrief
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionShortResponseDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("accountId")
    val accountId: Int = 0,
    @SerialName("categoryId")
    val categoryId: Int = 0,
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

    fun toDomain() = TransactionBrief(
        id = id,
        accountId = accountId,
        categoryId = categoryId,
        amount = amount.toDouble(),
        date = formatDtoDateToUiDate(transactionDate),
        comment = if (comment == "") null else comment
    )

    fun toEntity() = TransactionEntity(
        id = id,
        accountId = accountId,
        categoryId = categoryId,
        amount = amount,
        transactionDate = transactionDate,
        comment = if (comment == "") null else comment
    )
}