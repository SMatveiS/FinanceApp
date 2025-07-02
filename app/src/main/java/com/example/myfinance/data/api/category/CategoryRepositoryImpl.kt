package com.example.myfinance.data.api.category

import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.data.utils.map
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

        return categories.map { category ->
            category.map { it.toDomain() }
        }
    }

    override suspend fun getCategoryByType(isIncome: Boolean): NetworkResult<List<Category>> {
        val categories = safeApiCall {
            categoryRemoteDataSource.getCategoryByType(isIncome = isIncome)
        }

        return categories.map { category ->
            category.map { it.toDomain() }
        }
    }
}
