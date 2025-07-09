package com.example.myfinance.ui.feature.presentation.transactions_history.datepicker

import androidx.compose.runtime.Composable
import com.example.myfinance.ui.feature.presentation.transactions_history.viewmodel.TransactionsHistoryViewModel
import com.example.myfinance.ui.feature.presentation.transactions_history.viewmodel.TransactionsState
import java.time.ZoneId

/**
 * Открывает календарь выбора начала или конца даты периода в зависимости от действий пользователя
 */

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
