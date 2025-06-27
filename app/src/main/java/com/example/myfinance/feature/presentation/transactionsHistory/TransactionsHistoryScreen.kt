package com.example.myfinance.feature.presentation.transactionsHistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myfinance.R
import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.feature.utils.formatNumber
import com.example.myfinance.ui.components.AppListItem
import com.example.myfinance.ui.components.AppTopBar
import com.example.myfinance.ui.components.ErrorState
import com.example.myfinance.ui.components.LoadingState
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TransactionsHistoryScreen(
    onBackArrowClicked: () -> Unit
) {
    val viewModel: TransactionsHistoryViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    state.dialogType?.let {
        val dialogDate = when (it) {
            DialogType.START_DATE -> state.startDate
            DialogType.END_DATE -> state.endDate
        }

        DatePickerModal(
            selectedDate = dialogDate.atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli(),
            onDateSelected = viewModel::onDateSelected,
            onDismiss = viewModel::onDatePickerDismiss
        )
    }

    Scaffold (
        topBar = {
            AppTopBar(
                title = "Моя история",
                rightButtonIcon = R.drawable.analytic_button,
                leftButtonIcon = R.drawable.back_arrow,
                rightButtonDescription = "Аналитика",
                leftButtonDescription = "Назад",
                rightButtonAction = {/* Действие */},
                leftButtonAction = onBackArrowClicked
            ) },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        when (state.screenState) {
            ScreenState.SUCCESS -> {
                TransactionsHistoryContent(
                    transactions = state.transactions,
                    startDate = state.startDate,
                    endDate = state.endDate,
                    totalSum = state.totalSum,
                    onStartDatePickerOpen = viewModel::onStartDatePickerOpen ,
                    onEndDatePickerOpen = viewModel::onEndDatePickerOpen,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.ERROR -> {
                ErrorState(
                    message = state.errorMessage ?: "Неизвестная ошибка",
                    onRetry = viewModel::getTransactions,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.LOADING -> LoadingState(modifier = Modifier.padding(innerPadding))
        }

    }
}

@Composable
fun TransactionsHistoryContent(
    transactions: List<Transaction>,
    startDate: LocalDate,
    endDate: LocalDate,
    totalSum: Double,
    onStartDatePickerOpen: () -> Unit,
    onEndDatePickerOpen: () -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier) {
        item {
            val dateFormatter = remember {
                DateTimeFormatter.ofPattern("d MMMM y", Locale("ru"))
            }

            Column {
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
                    rightTitle = formatNumber(totalSum),
                    listBackground = MaterialTheme.colorScheme.secondary,
                    listHeight = 56
                )
                HorizontalDivider()
            }
        }

        items(transactions) { transaction ->
            TransactionListItem(transaction)
            HorizontalDivider()
        }
    }
}
