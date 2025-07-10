package com.example.myfinance.ui.feature.presentation.transactions_history.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfinance.R
import com.example.myfinance.app.LocalViewModelFactory
import com.example.myfinance.ui.common.AppTopBar
import com.example.myfinance.ui.common.ErrorState
import com.example.myfinance.ui.common.LoadingState
import com.example.myfinance.ui.feature.presentation.ScreenState
import com.example.myfinance.ui.feature.presentation.transactions_history.datepicker.OpenDatePicker
import com.example.myfinance.ui.feature.presentation.transactions_history.viewmodel.TransactionsHistoryViewModel

@Composable
fun TransactionsHistoryScreen(
    onBackArrowClicked: () -> Unit
) {

    val viewModel: TransactionsHistoryViewModel = viewModel(factory = LocalViewModelFactory.current)

    val state by viewModel.state.collectAsStateWithLifecycle()

    state.dialogType?.let {
        OpenDatePicker(
            dialogType = it,
            state = state,
            viewModel = viewModel
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
                    currency = state.currency,
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
