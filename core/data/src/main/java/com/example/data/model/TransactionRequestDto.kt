package com.example.data.model

import com.example.data.local.database.TransactionEntity
import com.example.data.utils.formatDtoDateToUiDate
import com.example.data.utils.formatUiDateToDtoDate
import com.example.model.Transaction
import com.example.model.TransactionBrief
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class TransactionRequestDto(
    @SerialName("accountId")
    val accountId: Int = 0,
    @SerialName("categoryId")
    val categoryId: Int = 0,
    @SerialName("amount")
    val amount: String = "500.00",
    @SerialName("transactionDate")
    val transactionDate: String = "2025-07-11T21:45:34.430Z",

    @EncodeDefault(EncodeDefault.Mode.ALWAYS)
    @SerialName("comment")
    val comment: String? = null
) {

    fun toEntity(id: Int) = TransactionEntity(
        id = id,
        accountId = accountId,
        categoryId = categoryId,
        amount = amount,
        transactionDate = transactionDate,
        comment = if (comment == "") null else comment,
        updatedAt = LocalDateTime.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT)
    )

    fun toDomain(id: Int) = TransactionBrief(
        id = id,
        accountId = accountId,
        categoryId = categoryId,
        amount = amount.toDouble(),
        date = formatDtoDateToUiDate(transactionDate),
        comment = if (comment == "") null else comment
    )
}

fun Transaction.toDto() = TransactionRequestDto(
    accountId = accountId,
    categoryId = category.id,
    amount = amount.toString(),
    transactionDate = formatUiDateToDtoDate(date),
    comment = comment
)