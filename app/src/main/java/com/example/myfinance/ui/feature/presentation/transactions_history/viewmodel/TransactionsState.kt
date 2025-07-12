package com.example.myfinance.ui.feature.presentation.transactions_history.viewmodel

import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.ui.feature.presentation.ScreenState
import com.example.myfinance.ui.feature.presentation.transactions_history.datepicker.DialogType
import java.time.LocalDate

/**
 * Состояние экрана истории транзакций
 */

data class TransactionsState(
    val transactions: List<Transaction> = emptyList(),
    val startDate: LocalDate = LocalDate.now().withDayOfMonth(1),
    val endDate: LocalDate = LocalDate.now(),
    val totalSum: Double = 0.0,
    val currency: String = "RUB",
    val dialogType: DialogType? = null,
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
