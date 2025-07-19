package com.example.myfinance.domain.repository

import com.example.myfinance.domain.model.Category

/**
 * Интерфейс репозитория категорий для доменного слоя
 */

interface CategoryRepository {

    suspend fun getAllCategories(): Result<List<Category>>

    suspend fun getCategoryByType(isIncome: Boolean): Result<List<Category>>

    suspend fun syncCategories(): Result<Unit>

}
