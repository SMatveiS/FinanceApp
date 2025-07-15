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
        childColumns = arrayOf("category_id")
    )]
)
data class TransactionEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo("account_id") val accountId: Long,
    @ColumnInfo("category_id") val categoryId: Long,
    val amount: String,
    @ColumnInfo("transaction_date") val transactionDate: String,
    val comment: String,
    @ColumnInfo("created_at") val createdAt: String,
    @ColumnInfo("updated_at") val updatedAt: String
)
