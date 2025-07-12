package com.example.myfinance.ui.feature.presentation.categories.viewmodel

import com.example.myfinance.domain.model.Category
import com.example.myfinance.ui.feature.presentation.ScreenState

/**
 * Состояние экрана статей
 */

data class CategoryState(
    val categories: List<Category> = emptyList(),
    val searchText: String = "",
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
