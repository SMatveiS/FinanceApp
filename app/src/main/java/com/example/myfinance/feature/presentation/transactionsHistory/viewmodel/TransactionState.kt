package com.example.myfinance.feature.presentation.transactionsHistory.viewmodel

import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.feature.presentation.ScreenState
import com.example.myfinance.feature.presentation.transactionsHistory.datepicker.DialogType
import java.time.LocalDate

/**
 * Состояние экрана истории транзакций
 */

data class TransactionsState(
    val transactions: List<Transaction> = emptyList(),
    val startDate: LocalDate = LocalDate.now().withDayOfMonth(1),
    val endDate: LocalDate = LocalDate.now(),
    val totalSum: Double = 0.0,
    val screenState: ScreenState = ScreenState.LOADING,
    val dialogType: DialogType? = null,
    val errorMessage: String? = null
)
