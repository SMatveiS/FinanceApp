package com.example.myfinance.ui.feature.presentation.categories.viewmodel

import com.example.model.Category
import com.example.ui.ScreenState

/**
 * Состояние экрана статей
 */

data class CategoryState(
    val categories: List<Category> = emptyList(),
    val searchText: String = "",
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
