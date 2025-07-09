package com.example.myfinance.domain.usecase.category

import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.data.utils.map
import com.example.myfinance.domain.model.Category
import javax.inject.Inject

/**
 * Возвращает категории, содержащие в имени substring
 */

class GetCategoriesWithSubstringUseCase @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) {

    suspend operator fun invoke(substring: String): NetworkResult<List<Category>> {
        val allCategories = getAllCategoriesUseCase()
        return allCategories.map { categories ->
            categories.filter { it.name.contains(substring, ignoreCase = true) }
        }
    }
}