package com.example.myfinance.ui.feature.presentation.incomes.viewmodel

import com.example.model.Transaction
import com.example.myfinance.ui.feature.presentation.ScreenState

/**
 * Состояние экрана доходов
 */

data class IncomesState(
    val incomes: List<Transaction> = emptyList(),
    val totalSum: Double = 0.0,
    val currency: String = "RUB",
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
