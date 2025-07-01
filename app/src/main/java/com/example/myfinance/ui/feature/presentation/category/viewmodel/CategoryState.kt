package com.example.myfinance.ui.feature.presentation.category.viewmodel

import com.example.myfinance.domain.model.Category
import com.example.myfinance.ui.feature.presentation.ScreenState

data class CategoryState(
    val categories: List<Category> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
