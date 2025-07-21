package com.example.domain.usecase.category

import com.example.data.repository.external.CategoryRepository
import javax.inject.Inject

/**
 * Возвращает все категории
 */

class GetAllCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke() = categoryRepository.getAllCategories()
}