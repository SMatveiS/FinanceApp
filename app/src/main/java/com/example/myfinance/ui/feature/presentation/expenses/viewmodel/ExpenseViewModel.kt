package com.example.myfinance.ui.feature.presentation.expenses.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.domain.usecase.transaction.GetTodayTransactionsUseCase
import com.example.myfinance.ui.feature.presentation.ScreenState
import com.example.myfinance.data.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Хранит состояние экрана расходов
 */

@HiltViewModel
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
                when (expensesResult) {
                    is NetworkResult.Success -> {
                        val currency = expensesResult.data.currency

                        _state.update { it.copy(
                            expenses = expensesResult.data.transactions,
                            totalSum = expensesResult.data.transactionsSum,
                            currency = currency,
                            screenState = ScreenState.SUCCESS
                        ) }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                errorMessage = expensesResult.errorMessage,
                                screenState = ScreenState.ERROR
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _state.update { it.copy(
                    errorMessage = "Ошибка: ${e.localizedMessage ?: "Неизвестная ошибка"}",
                    screenState = ScreenState.ERROR
                ) }
            }
        }
    }
}
