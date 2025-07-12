package com.example.myfinance.domain.usecase.category

import com.example.myfinance.domain.repository.CategoryRepository
import javax.inject.Inject

/**
 * Возвращает все категории
 */

class GetAllCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke() = categoryRepository.getAllCategories()
}