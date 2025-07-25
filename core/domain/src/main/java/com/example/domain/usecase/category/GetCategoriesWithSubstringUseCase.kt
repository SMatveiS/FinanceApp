package com.example.domain.usecase.category

import com.example.model.Category
import javax.inject.Inject

/**
 * Возвращает категории, содержащие в имени substring
 */

class GetCategoriesWithSubstringUseCase @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) {

    suspend operator fun invoke(substring: String): Result<List<Category>> {
        val allCategories = getAllCategoriesUseCase()

        return allCategories.map { categories ->
            categories.filter { it.name.contains(substring, ignoreCase = true) }
        }
    }
}