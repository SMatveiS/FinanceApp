package com.example.myfinance.ui.feature.presentation.analysis.viewmodel

import com.example.myfinance.domain.model.CategoryStatistic
import com.example.ui.datepicker.DatePickerDialogType
import com.example.myfinance.ui.feature.presentation.ScreenState
import java.time.LocalDate

data class AnalysisState(
    val categoriesStatistic: List<CategoryStatistic> = emptyList(),
    val startDate: LocalDate = LocalDate.now().withDayOfMonth(1),
    val endDate: LocalDate = LocalDate.now(),
    val totalSum: Double = 0.0,
    val currency: String = "RUB",
    val datePickerDialogType: DatePickerDialogType? = null,
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
