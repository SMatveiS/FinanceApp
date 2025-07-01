package com.example.myfinance.data.api.category

import com.example.myfinance.domain.repository.CategoryRepository
import com.example.myfinance.data.utils.safeApiCall
import javax.inject.Inject

/**
 * Возвращает информацию о категориях внутри NetworkResult независимо от источника
 */

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteDataSource: CategoryRemoteDataSource
): CategoryRepository {

    override suspend fun getAllCategories() =
        safeApiCall { categoryRemoteDataSource.getAllCategories() }

    override suspend fun getCategoryByType(isIncome: Boolean) =
        safeApiCall { categoryRemoteDataSource.getCategoryByType(isIncome = isIncome) }

}
