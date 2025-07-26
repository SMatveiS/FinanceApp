package com.example.ui.datepicker

import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.ZoneId

/**
 * Открывает календарь выбора начала или конца даты периода в зависимости от действий пользователя
 */

@Composable
fun OpenDatePicker(
    dialogType: DatePickerDialogType,
    startDate: LocalDate,
    endDate: LocalDate,
    onDateSelected: (Long?) -> Unit,
    onDatePickerDismiss: () -> Unit
) {

    val dialogDate = when (dialogType) {
        DatePickerDialogType.START_DATE -> startDate
        DatePickerDialogType.END_DATE -> endDate
    }

    DatePickerModal(
        selectedDate = dialogDate.atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli(),
        onDateSelected = onDateSelected,
        onDismiss = onDatePickerDismiss
    )
}
