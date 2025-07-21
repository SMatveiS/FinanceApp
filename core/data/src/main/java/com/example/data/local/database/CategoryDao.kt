package com.example.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM Categories")
    suspend fun getAllCategories(): List<CategoryEntity>

    @Query("SELECT * FROM Categories WHERE is_income = :isIncome")
    suspend fun getCategoriesByType(isIncome: Boolean): List<CategoryEntity>
}