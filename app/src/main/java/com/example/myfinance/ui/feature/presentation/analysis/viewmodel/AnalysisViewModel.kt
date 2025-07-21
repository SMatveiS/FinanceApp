package com.example.myfinance.ui.feature.presentation.analysis.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.domain.usecase.transaction.GetCategoryStatisticForPeriodUseCase
import com.example.ui.datepicker.DatePickerDialogType
import com.example.myfinance.ui.feature.presentation.ScreenState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class AnalysisViewModel @AssistedInject constructor(
    private val getCategoryStatisticForPeriodUseCase: GetCategoryStatisticForPeriodUseCase,
    @Assisted private val isIncome: Boolean
): ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(isIncome: Boolean): AnalysisViewModel
    }

    private val _state = MutableStateFlow(AnalysisState())
    val state = _state.asStateFlow()

    init {
        getStatistic()
    }

    fun getStatistic() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    screenState = ScreenState.LOADING,
                    errorMessage = null
                )
            }

            try {
                val startDate = state.value.startDate.format(DateTimeFormatter.ofPattern("y-MM-dd"))
                val endDate = state.value.endDate.format(DateTimeFormatter.ofPattern("y-MM-dd"))

                val statisticInfoResult = getCategoryStatisticForPeriodUseCase(
                    startDate = startDate,
                    endDate = endDate,
                    isIncomes = isIncome
                )

                statisticInfoResult.fold(
                    onSuccess = { statisticInfo ->
                        _state.update {
                            it.copy(
                                categoriesStatistic = statisticInfo.categoryStatistics,
                                totalSum = statisticInfo.totalSum,
                                currency = statisticInfo.currency,
                                screenState = ScreenState.SUCCESS
                            )
                        }
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
                _state.update {
                    it.copy(
                        errorMessage = e.message,
                        screenState = ScreenState.ERROR
                    )
                }
            }
        }
    }

    fun onStartDatePickerOpen() = openDialog(DatePickerDialogType.START_DATE)

    fun onEndDatePickerOpen() = openDialog(DatePickerDialogType.END_DATE)

    private fun openDialog(type: DatePickerDialogType) {
        _state.update { it.copy(datePickerDialogType = type) }
    }

    fun onDatePickerDismiss() {
        _state.update { it. copy(datePickerDialogType = null) }
    }

    fun onDateSelected(dateInMillis: Long?) {
        if (dateInMillis != null) {
            val newDate = Instant.ofEpochMilli(dateInMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

            _state.update { currentState ->
                val (newStart, newEnd) = when (currentState.datePickerDialogType) {
                    DatePickerDialogType.START_DATE -> newDate to currentState.endDate
                    DatePickerDialogType.END_DATE -> currentState.startDate to newDate
                    null -> return
                }

                if (newStart.isAfter(newEnd)) return@update currentState

                currentState.copy(
                    startDate = newStart,
                    endDate = newEnd,
                    datePickerDialogType = null
                )
            }

            getStatistic()
        }
    }
}