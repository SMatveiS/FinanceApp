package com.example.myfinance.ui.feature.presentation.transactionsHistory.screen

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.myfinance.ui.common.AppListItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Показывает титлы экрана истории транзакций
 */

@Composable
fun TransactionHistoryTitles(
    startDate: LocalDate,
    endDate: LocalDate,
    totalSum: String,
    onStartDatePickerOpen: () -> Unit,
    onEndDatePickerOpen: () -> Unit,
) {

    val dateFormatter = remember {
        DateTimeFormatter.ofPattern("d MMMM y", Locale("ru"))
    }

    AppListItem(
        leftTitle = "Начало",
        rightTitle = startDate.format(dateFormatter),
        listBackground = MaterialTheme.colorScheme.secondary,
        listHeight = 56,
        clickable = true,
        onClick = onStartDatePickerOpen
    )
    HorizontalDivider()

    AppListItem(
        leftTitle = "Конец",
        rightTitle = endDate.format(dateFormatter),
        listBackground = MaterialTheme.colorScheme.secondary,
        listHeight = 56,
        clickable = true,
        onClick = onEndDatePickerOpen
    )
    HorizontalDivider()

    AppListItem(
        leftTitle = "Сумма",
        rightTitle = totalSum,
        listBackground = MaterialTheme.colorScheme.secondary,
        listHeight = 56
    )
}
