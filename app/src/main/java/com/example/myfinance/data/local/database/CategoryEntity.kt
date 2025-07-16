package com.example.myfinance.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myfinance.domain.model.Category

@Entity(tableName = "Categories")
data class CategoryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val emoji: String,
    @ColumnInfo("is_income") val isIncome: Boolean
) {

    fun toDomain() = Category(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )
}