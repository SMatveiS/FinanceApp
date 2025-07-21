package com.example.data.repository.external

import com.example.model.Category

/**
 * Интерфейс репозитория категорий для доменного слоя
 */

interface CategoryRepository {

    suspend fun getAllCategories(): Result<List<Category>>

    suspend fun getCategoryByType(isIncome: Boolean): Result<List<Category>>

    suspend fun syncCategories(): Result<Unit>

}
