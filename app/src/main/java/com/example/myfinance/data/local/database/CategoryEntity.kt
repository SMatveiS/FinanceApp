package com.example.myfinance.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categories")
data class CategoryEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val emoji: String,
    @ColumnInfo("is_income") val isIncome: Boolean
)