package com.example.myfinance.domain.repository

import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.domain.model.Category

/**
 * Интерфейс репозитория категорий для доменного слоя
 */

interface CategoryRepository {

    suspend fun getAllCategories(): NetworkResult<List<Category>>

    suspend fun getCategoryByType(isIncome: Boolean): NetworkResult<List<Category>>

}
