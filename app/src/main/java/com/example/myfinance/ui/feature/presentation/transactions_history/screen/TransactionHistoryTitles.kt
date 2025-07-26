package com.example.myfinance.ui.feature.presentation.transactions_history.screen

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.ui.AppListItem
import com.example.ui.uiDateFormat
import java.time.LocalDate

@Composable
fun TransactionHistoryTitles(
    startDate: LocalDate,
    endDate: LocalDate,
    totalSum: String,
    onStartDatePickerOpen: () -> Unit,
    onEndDatePickerOpen: () -> Unit,
) {

    AppListItem(
        leftTitle = "Начало",
        rightTitle = startDate.format(uiDateFormat),
        itemBackground = MaterialTheme.colorScheme.secondary,
        itemHeight = 56,
        clickable = true,
        onClick = onStartDatePickerOpen
    )
    HorizontalDivider()

    AppListItem(
        leftTitle = "Конец",
        rightTitle = endDate.format(uiDateFormat),
        itemBackground = MaterialTheme.colorScheme.secondary,
        itemHeight = 56,
        clickable = true,
        onClick = onEndDatePickerOpen
    )
    HorizontalDivider()

    AppListItem(
        leftTitle = "Сумма",
        rightTitle = totalSum,
        itemBackground = MaterialTheme.colorScheme.secondary,
        itemHeight = 56
    )
}
