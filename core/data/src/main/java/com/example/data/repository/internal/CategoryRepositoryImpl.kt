package com.example.data.repository.internal

import com.example.data.local.database.CategoryDao
import com.example.data.remote.category.CategoryRemoteDataSource
import com.example.data.utils.safeApiCall
import com.example.model.Category
import com.example.data.repository.external.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.collections.map

/**
 * Возвращает информацию о категориях внутри NetworkResult независимо от источника
 */

class CategoryRepositoryImpl @Inject constructor(
    private val remoteDataSource: CategoryRemoteDataSource,
    private val localDataSource: CategoryDao
): CategoryRepository {

    override suspend fun getAllCategories(): Result<List<Category>> {
        return withContext(Dispatchers.IO) {

            val localCategories = localDataSource.getAllCategories()
            if (localCategories.isNotEmpty()) {
                return@withContext Result.success(localCategories.map { it.toDomain() })
            }

            // Если списка категорий нет в бд, то делаем запрос на сервер
            val categoriesResult = safeApiCall { remoteDataSource.getAllCategories() }
            categoriesResult.map { categories ->
                localDataSource.addCategories(categories.map { it.toEntity() })
                categories.map { it.toDomain() }
            }
        }
    }

    override suspend fun getCategoryByType(isIncome: Boolean): Result<List<Category>> {
        return withContext(Dispatchers.IO) {

            val localCategories = localDataSource.getCategoriesByType(isIncome)
            if (localCategories.isNotEmpty()) {
                return@withContext Result.success(localCategories.map { it.toDomain() })
            }

            // Если списка категорий нет в бд, то делаем запрос на сервер
            val categoriesResult = safeApiCall {
                remoteDataSource.getCategoryByType(isIncome = isIncome)
            }

            categoriesResult.map { categories ->
                localDataSource.addCategories(categories.map { it.toEntity() })
                categories.map { it.toDomain() }
            }
        }
    }

    override suspend fun syncCategories(): Result<Unit> {
        return withContext(Dispatchers.IO) {

            val categoriesResult = safeApiCall { remoteDataSource.getAllCategories() }

            categoriesResult.map { categories ->
                localDataSource.addCategories(categories.map { it.toEntity() })
                return@map
            }
        }
    }
}