package com.example.myfinance.data.local.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CategoryDao {

    @Query("SELECT * FROM Categories")
    suspend fun getAllCategories(): List<CategoryEntity>

    @Query("SELECT * FROM Categories WHERE is_income = :isIncome")
    suspend fun getCategoriesByType(isIncome: Boolean): List<CategoryEntity>
}