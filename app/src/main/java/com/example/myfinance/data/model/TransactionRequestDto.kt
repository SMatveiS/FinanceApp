package com.example.myfinance.data.model

import com.example.myfinance.data.local.database.TransactionEntity
import com.example.myfinance.data.utils.formatDtoDateToUiDate
import com.example.myfinance.domain.model.TransactionBrief
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
    val accountId: Int = 1,
    @SerialName("categoryId")
    val categoryId: Int = 1,
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