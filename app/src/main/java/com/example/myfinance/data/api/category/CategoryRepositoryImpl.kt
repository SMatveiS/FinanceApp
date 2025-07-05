package com.example.myfinance.data.api.category

import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.data.utils.map
import com.example.myfinance.domain.repository.CategoryRepository
import com.example.myfinance.data.utils.safeApiCall
import com.example.myfinance.domain.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Возвращает информацию о категориях внутри NetworkResult независимо от источника
 */

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteDataSource: CategoryRemoteDataSource
): CategoryRepository {

    override suspend fun getAllCategories(): NetworkResult<List<Category>> {
        return withContext(Dispatchers.IO) {

            val categories = safeApiCall { categoryRemoteDataSource.getAllCategories() }

            categories.map { category ->
                category.map { it.toDomain() }
            }
        }
    }

    override suspend fun getCategoryByType(isIncome: Boolean): NetworkResult<List<Category>> {
        return withContext(Dispatchers.IO) {

            val categories = safeApiCall {
                categoryRemoteDataSource.getCategoryByType(isIncome = isIncome)
            }

            categories.map { category ->
                category.map { it.toDomain() }
            }
        }
    }
}
