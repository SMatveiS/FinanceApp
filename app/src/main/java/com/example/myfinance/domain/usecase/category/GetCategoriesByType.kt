package com.example.myfinance.domain.usecase.category

import com.example.myfinance.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesByType @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(isIncome: Boolean) = categoryRepository.getCategoryByType(isIncome)
}