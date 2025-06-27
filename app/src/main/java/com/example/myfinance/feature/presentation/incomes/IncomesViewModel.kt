package com.example.myfinance.feature.presentation.incomes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.feature.domain.usecase.GetTodayTransactionsUseCase
import com.example.myfinance.feature.presentation.transactionsHistory.ScreenState
import com.example.myfinance.feature.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class IncomesViewModel @Inject constructor(
    private val getTodayTransactionsUseCase: GetTodayTransactionsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<IncomesState>(IncomesState())
    val state: StateFlow<IncomesState> = _state

    init {
        getExpenses()
    }

    fun getExpenses() {
        viewModelScope.launch {
            _state.update { it.copy(screenState = ScreenState.LOADING) }

            try {
                val transactionsResult = getTodayTransactionsUseCase()

                when (transactionsResult) {
                    is NetworkResult.Success -> {
                        val transactions = transactionsResult.data ?: emptyList()

                        val sortedTransactions = transactions
                            .filter { it.category.isIncome == true }
                            .sortedByDescending { it.date }

                        val totalSum = sortedTransactions.sumOf { it.amount }

                        _state.update { it.copy(
                            sortedTransactions,
                            totalSum = totalSum,
                            screenState = ScreenState.SUCCESS
                        ) }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                errorMessage = transactionsResult.errorMessage,
                                screenState = ScreenState.ERROR
                            )
                        }
                    }

                    is NetworkResult.Loading -> {}
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

data class IncomesState(
    val expenses: List<Transaction> = emptyList(),
    val totalSum: Double = 0.0,
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)