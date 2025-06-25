package com.example.myfinance.data.api.category

import com.example.myfinance.data.model.CategoryDto
import com.example.myfinance.feature.domain.repository.CategoryRepository
import com.example.myfinance.feature.utils.BaseApiResponse
import com.example.myfinance.feature.utils.NetworkResult
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteDataSource: CategoryRemoteDataSource
): CategoryRepository {

    override suspend fun getAllCategories() =
        categoryRemoteDataSource.getAllCategories()

    override suspend fun getCategoryByType(isIncome: Boolean) =
        categoryRemoteDataSource.getCategoryByType(isIncome = isIncome)

}