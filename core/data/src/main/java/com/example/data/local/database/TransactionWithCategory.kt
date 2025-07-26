package com.example.data.local.database

import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.utils.formatDtoDateToUiDate
import com.example.model.Transaction

data class TransactionWithCategory(
    @Embedded val transaction: TransactionEntity,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: CategoryEntity
) {

    fun toDomain() = Transaction(
        id = transaction.id,
        accountId = transaction.accountId,
        category = category.toDomain(),
        amount = transaction.amount.toDouble(),
        date = formatDtoDateToUiDate(transaction.transactionDate),
        comment = transaction.comment
    )
}