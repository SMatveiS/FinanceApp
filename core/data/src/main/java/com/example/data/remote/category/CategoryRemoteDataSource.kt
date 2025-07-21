package com.example.data.remote.category

import javax.inject.Inject

/**
 * Удалённый источник данных, связанных с категориями
 */

class CategoryRemoteDataSource @Inject constructor(
    private val categoryApi: CategoryApi
) {

    suspend fun getAllCategories() = categoryApi.getAllCategories()

    suspend fun getCategoryByType(isIncome: Boolean) = categoryApi.getCategoriesByType(isIncome = isIncome)
}
