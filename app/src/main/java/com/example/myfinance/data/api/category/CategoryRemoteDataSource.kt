package com.example.myfinance.data.api.category


class CategoryRemoteDataSource(
    private val categoryApi: CategoryApi
) {
    suspend fun getAllCategories() = categoryApi.getAllCategories()
    suspend fun getCategoryByType(isIncome: Boolean) = categoryApi.getCategoriesByType(isIncome = isIncome)
}