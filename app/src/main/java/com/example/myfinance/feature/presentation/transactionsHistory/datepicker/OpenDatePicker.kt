package com.example.myfinance.feature.presentation.transactionsHistory.datepicker

import androidx.compose.runtime.Composable
import com.example.myfinance.feature.presentation.transactionsHistory.viewmodel.TransactionsHistoryViewModel
import com.example.myfinance.feature.presentation.transactionsHistory.viewmodel.TransactionsState
import java.time.ZoneId

@Composable
fun OpenDatePicker(
    dialogType: DialogType,
    state: TransactionsState,
    viewModel: TransactionsHistoryViewModel
) {

    val dialogDate = when (dialogType) {
        DialogType.START_DATE -> state.startDate
        DialogType.END_DATE -> state.endDate
    }

    DatePickerModal(
        selectedDate = dialogDate.atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli(),
        onDateSelected = viewModel::onDateSelected,
        onDismiss = viewModel::onDatePickerDismiss
    )
}
