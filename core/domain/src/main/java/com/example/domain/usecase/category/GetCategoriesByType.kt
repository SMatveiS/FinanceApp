package com.example.domain.usecase.category

import com.example.data.repository.external.CategoryRepository
import javax.inject.Inject

class GetCategoriesByType @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(isIncome: Boolean) = categoryRepository.getCategoryByType(isIncome)
}