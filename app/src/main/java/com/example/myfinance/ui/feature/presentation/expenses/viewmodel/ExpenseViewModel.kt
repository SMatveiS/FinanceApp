package com.example.myfinance.ui.feature.presentation.expenses.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.transaction.GetTodayTransactionsUseCase
import com.example.ui.screenstate.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Хранит состояние экрана расходов
 */

class ExpenseViewModel @Inject constructor(
    private val getTodayTransactionsUseCase: GetTodayTransactionsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ExpensesState>(ExpensesState())
    val state = _state.asStateFlow()

    init {
        getExpenses()
    }

    fun getExpenses() {
        viewModelScope.launch {
            _state.update { it.copy(
                screenState = ScreenState.LOADING,
                errorMessage = null
            ) }

            try {
                val expensesResult = getTodayTransactionsUseCase(isIncomes = false)
                expensesResult.fold(
                    onSuccess = { expenses ->
                        _state.update { it.copy(
                            expenses = expenses.transactions,
                            totalSum = expenses.transactionsSum,
                            currency = expenses.currency,
                            screenState = ScreenState.SUCCESS
                        ) }
                    },

                    onFailure = { error ->
                        _state.update {
                            it.copy(
                                errorMessage = error.message,
                                screenState = ScreenState.ERROR
                            )
                        }
                    }
                )
            } catch (e: Exception) {
                _state.update { it.copy(
                    errorMessage = e.message,
                    screenState = ScreenState.ERROR
                ) }
            }
        }
    }
}
