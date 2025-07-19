package com.example.myfinance.data.remote.category

import com.example.myfinance.data.model.CategoryResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Интерфейс для связанных с категориями запросов в сеть
 */

interface CategoryApi {
    @GET("categories")
    suspend fun getAllCategories(): Response<List<CategoryResponseDto>>

    @GET("categories/type/{isIncome}")
    suspend fun getCategoriesByType(@Path("isIncome") isIncome: Boolean): Response<List<CategoryResponseDto>>
}
