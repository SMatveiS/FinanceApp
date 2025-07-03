package com.example.myfinance.ui.feature.presentation.incomes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.domain.usecase.GetTodayTransactionsUseCase
import com.example.myfinance.ui.feature.presentation.ScreenState
import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.ui.feature.presentation.account.screen.getCurrencySymbol
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Хранит состояние экрана доходов
 */

@HiltViewModel
class IncomesViewModel @Inject constructor(
    private val getTodayTransactionsUseCase: GetTodayTransactionsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<IncomesState>(IncomesState())
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
                val incomesResult = getTodayTransactionsUseCase(isIncomes = true)

                when (incomesResult) {
                    is NetworkResult.Success -> {
                        val currency = getCurrencySymbol(incomesResult.data.currency)

                        _state.update { it.copy(
                            incomes = incomesResult.data.transactions,
                            totalSum = incomesResult.data.transactionsSum,
                            currency = currency,
                            screenState = ScreenState.SUCCESS
                        ) }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                errorMessage = incomesResult.errorMessage,
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
