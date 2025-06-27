package com.example.myfinance.feature.presentation.incomes.viewmodel

import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.feature.presentation.ScreenState

data class IncomesState(
    val expenses: List<Transaction> = emptyList(),
    val totalSum: Double = 0.0,
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
