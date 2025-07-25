package com.example.myfinance.ui.feature.presentation.expenses.viewmodel

import com.example.model.Transaction
import com.example.ui.screenstate.ScreenState

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
