package com.example.data.local.database.pending_operation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.TransactionRequestDto

@Entity(tableName = "pending_operations")
data class PendingOperationEntity(
    @PrimaryKey val id: Int,
    val type: String,
    @ColumnInfo("updated_at") val updatedAt: Long,
    @ColumnInfo("account_id") val accountId: Int?,
    @ColumnInfo("category_id") val categoryId: Int?,
    val amount: String?,
    @ColumnInfo("transaction_date") val transactionDate: String?,
    val comment: String?
) {

    fun toDto() = TransactionRequestDto(
        accountId = accountId!!,
        categoryId = categoryId!!,
        amount = amount!!,
        transactionDate = transactionDate!!,
        comment = comment
    )
}