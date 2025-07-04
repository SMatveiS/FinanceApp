package com.example.myfinance.ui.feature.presentation.expenses.viewmodel

import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.ui.feature.presentation.ScreenState

/**
 * Состояние экрана расходов
 */

data class ExpensesState(
    val expenses: List<Transaction> = emptyList(),
    val totalSum: Double = 0.0,
    val currency: String = "RUB",
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
