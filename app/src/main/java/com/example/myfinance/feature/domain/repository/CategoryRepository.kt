package com.example.myfinance.feature.domain.repository

import com.example.myfinance.data.model.CategoryDto
import retrofit2.Response

/**
 * Интерфейс репозитория категорий для доменного слоя
 */

interface CategoryRepository {

    suspend fun getAllCategories(): Response<List<CategoryDto>>

    suspend fun getCategoryByType(isIncome: Boolean): Response<List<CategoryDto>>

}
