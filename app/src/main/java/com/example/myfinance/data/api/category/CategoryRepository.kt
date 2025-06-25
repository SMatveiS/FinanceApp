package com.example.myfinance.data.api.category

import com.example.myfinance.data.model.CategoryDto
import com.example.myfinance.feature.utils.BaseApiResponse
import com.example.myfinance.feature.utils.NetworkResult

class CategoryRepository(
    private val categoryRemoteDataSource: CategoryRemoteDataSource
): BaseApiResponse() {

    suspend fun getAllCategories(): NetworkResult<List<CategoryDto>> {
        return safeApiCall { categoryRemoteDataSource.getAllCategories() }
    }

    suspend fun getCategoryByType(isIncome: Boolean): NetworkResult<List<CategoryDto>> {
        return safeApiCall { categoryRemoteDataSource.getCategoryByType(isIncome = isIncome) }
    }

}