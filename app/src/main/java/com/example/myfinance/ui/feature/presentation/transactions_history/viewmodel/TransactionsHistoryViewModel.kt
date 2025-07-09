package com.example.myfinance.ui.feature.presentation.transactions_history.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.domain.usecase.transaction.GetTransactionsForPeriodUseCase
import com.example.myfinance.ui.feature.presentation.transactions_history.datepicker.DialogType
import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.ui.feature.presentation.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Хранит состояние экрана истории транзакций
 */

@HiltViewModel
class TransactionsHistoryViewModel @Inject constructor(
    private val getTransactionsForPeriodUseCase: GetTransactionsForPeriodUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(TransactionsState())
    val state = _state.asStateFlow()

    private val isIncomes: Boolean = savedStateHandle.get<String>("source") != "expenses"

    init {
        getTransactions()
    }

    fun onStartDatePickerOpen() = openDialog(DialogType.START_DATE)

    fun onEndDatePickerOpen() = openDialog(DialogType.END_DATE)

    private fun openDialog(type: DialogType) {
        _state.update { it.copy(dialogType = type) }
    }

    fun onDatePickerDismiss() {
        _state.update { it. copy(dialogType = null) }
    }

    fun onDateSelected(dateInMillis: Long?) {
        if (dateInMillis != null) {
            val newDate = Instant.ofEpochMilli(dateInMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

            _state.update { currentState ->
                val (newStart, newEnd) = when (currentState.dialogType) {
                    DialogType.START_DATE -> newDate to currentState.endDate
                    DialogType.END_DATE -> currentState.startDate to newDate
                    null -> return
                }

                if (newStart.isAfter(newEnd)) return@update currentState

                currentState.copy(
                    startDate = newStart,
                    endDate = newEnd,
                    dialogType = null
                )
            }

            getTransactions()
        }
    }

    fun getTransactions() {
        viewModelScope.launch {
            _state.update { it.copy(
                screenState = ScreenState.LOADING,
                errorMessage = null
            ) }

            try {
                val startDate = state.value.startDate.format(DateTimeFormatter.ofPattern("y-MM-dd"))
                val endDate = state.value.endDate.format(DateTimeFormatter.ofPattern("y-MM-dd"))

                val transactionsResult = getTransactionsForPeriodUseCase(
                    startDate = startDate,
                    endDate = endDate,
                    isIncomes = isIncomes
                )

                when (transactionsResult) {
                    is NetworkResult.Success -> {
                        val currency = transactionsResult.data.currency

                        _state.update { it.copy(
                            transactions = transactionsResult.data.transactions,
                            totalSum = transactionsResult.data.transactionsSum,
                            currency = currency,
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
