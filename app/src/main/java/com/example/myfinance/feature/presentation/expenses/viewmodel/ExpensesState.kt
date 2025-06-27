package com.example.myfinance.feature.presentation.expenses.viewmodel

import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.feature.presentation.ScreenState

data class ExpensesState(
    val expenses: List<Transaction> = emptyList(),
    val totalSum: Double = 0.0,
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
