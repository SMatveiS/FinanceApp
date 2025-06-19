package com.example.myfinance.data.api

import com.example.myfinance.data.dto.CategoryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoriesApi {
    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    @GET("categories/type/{isIncome}")
    suspend fun getCategoriesByType(@Path("isIncome") isIncome: Boolean): List<CategoryDto>
}