package com.example.myfinance.ui.feature.presentation.transactions_history.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myfinance.R
import com.example.myfinance.app.MainActivity
import com.example.myfinance.app.findActivity
import com.example.ui.AppTopBar
import com.example.ui.screenstate.ErrorState
import com.example.ui.screenstate.LoadingState
import com.example.ui.datepicker.OpenDatePicker
import com.example.ui.screenstate.ScreenState
import com.example.myfinance.ui.feature.presentation.transactions_history.viewmodel.TransactionsHistoryViewModel

@Composable
fun TransactionsHistoryScreen(
    isIncome: Boolean,
    onBackArrowClicked: () -> Unit,
    onAnalysisClicked: () -> Unit,
    onItemClicked: (Int) -> Unit
) {

    val context = LocalContext.current
    val activity = context.findActivity() as MainActivity

    val assistedFactory = remember {
        activity.activityComponent.assistedTransactionsHistoryFactory()
    }

    val viewModel: TransactionsHistoryViewModel = remember(isIncome) {
        assistedFactory.create(isIncome)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    state.datePickerDialogType?.let {
        OpenDatePicker(
            dialogType = it,
            startDate = state.startDate,
            endDate = state.endDate,
            onDateSelected = viewModel::onDateSelected,
            onDatePickerDismiss = viewModel::onDatePickerDismiss
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
                rightButtonAction = onAnalysisClicked,
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
                    onStartDatePickerOpen = viewModel::onStartDatePickerOpen,
                    onEndDatePickerOpen = viewModel::onEndDatePickerOpen,
                    onItemClicked = onItemClicked,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.ERROR -> {
                ErrorState(
                    message = state.errorMessage,
                    onRetry = viewModel::getTransactions,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.LOADING -> LoadingState(modifier = Modifier.padding(innerPadding))
        }

    }
}
