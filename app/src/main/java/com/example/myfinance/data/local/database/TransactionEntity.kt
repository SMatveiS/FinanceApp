package com.example.myfinance.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Transactions",
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("category_id"),
        onUpdate = ForeignKey.CASCADE
    )]
)
data class TransactionEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("account_id") val accountId: Int,
    @ColumnInfo("category_id") val categoryId: Int,
    val amount: String,
    @ColumnInfo("transaction_date") val transactionDate: String,
    val comment: String?,
    @ColumnInfo("updated_at") val updatedAt: String
)
