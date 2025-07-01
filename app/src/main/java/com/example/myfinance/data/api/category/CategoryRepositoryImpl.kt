package com.example.myfinance.data.api.category

import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.domain.repository.CategoryRepository
import com.example.myfinance.data.utils.safeApiCall
import com.example.myfinance.domain.model.Category
import javax.inject.Inject

/**
 * Возвращает информацию о категориях внутри NetworkResult независимо от источника
 */

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteDataSource: CategoryRemoteDataSource
): CategoryRepository {

    override suspend fun getAllCategories(): NetworkResult<List<Category>> {
        val categories = safeApiCall { categoryRemoteDataSource.getAllCategories() }

        return when (categories) {
            is NetworkResult.Success ->
                NetworkResult.Success(categories.data.map { it.toDomain() })

            is NetworkResult.Error -> NetworkResult.Error(errorMessage = categories.errorMessage)
        }
    }

    override suspend fun getCategoryByType(isIncome: Boolean): NetworkResult<List<Category>> {
        val categories = safeApiCall {
            categoryRemoteDataSource.getCategoryByType(isIncome = isIncome)
        }

        return when (categories) {
            is NetworkResult.Success ->
                NetworkResult.Success(categories.data.map { it.toDomain() })

            is NetworkResult.Error -> NetworkResult.Error(errorMessage = categories.errorMessage)
        }
    }
}
