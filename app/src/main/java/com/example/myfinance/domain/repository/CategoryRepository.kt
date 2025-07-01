package com.example.myfinance.domain.repository

import com.example.myfinance.data.model.CategoryDto
import com.example.myfinance.data.utils.NetworkResult

/**
 * Интерфейс репозитория категорий для доменного слоя
 */

interface CategoryRepository {

    suspend fun getAllCategories(): NetworkResult<List<CategoryDto>>

    suspend fun getCategoryByType(isIncome: Boolean): NetworkResult<List<CategoryDto>>

}
