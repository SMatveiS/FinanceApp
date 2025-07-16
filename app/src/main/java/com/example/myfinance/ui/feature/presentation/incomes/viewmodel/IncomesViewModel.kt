package com.example.myfinance.ui.feature.presentation.incomes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.domain.usecase.transaction.GetTodayTransactionsUseCase
import com.example.myfinance.ui.feature.presentation.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Хранит состояние экрана доходов
 */

class IncomesViewModel @Inject constructor(
    private val getTodayTransactionsUseCase: GetTodayTransactionsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<IncomesState>(IncomesState())
    val state = _state.asStateFlow()

    init {
        getIncomes()
    }

    fun getIncomes() {
        viewModelScope.launch {
            _state.update { it.copy(
                screenState = ScreenState.LOADING,
                errorMessage = null
            ) }

            try {
                val incomesResult = getTodayTransactionsUseCase(isIncomes = true)

                incomesResult.fold(
                    onSuccess = { incomes ->
                        _state.update { it.copy(
                            incomes = incomes.transactions,
                            totalSum = incomes.transactionsSum,
                            currency = incomes.currency,
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
