package com.example.myfinance.ui.feature.presentation.expenses.screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myfinance.R
import com.example.myfinance.ui.feature.presentation.ScreenState
import com.example.myfinance.ui.feature.presentation.expenses.viewmodel.ExpenseViewModel
import com.example.myfinance.ui.common.AppFAB
import com.example.myfinance.ui.common.AppTopBar
import com.example.myfinance.ui.common.ErrorState
import com.example.myfinance.ui.common.LoadingState

@Composable
fun ExpensesScreen(
    viewModel: ExpenseViewModel = hiltViewModel(),
    onHistoryClicked: () -> Unit,
    onFabClicked: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold (
        topBar = {
            AppTopBar(
                title = "Расходы сегодня",
                rightButtonIcon = R.drawable.history,
                rightButtonDescription = "История",
                rightButtonAction = onHistoryClicked
            ) },

        floatingActionButton = { AppFAB(onClick = onFabClicked) },

        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        when (state.screenState) {
            ScreenState.SUCCESS -> {
                ExpensesContent(
                    expenses = state.expenses,
                    totalSum = state.totalSum,
                    currency = state.currency,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.ERROR -> {
                ErrorState(
                    message = state.errorMessage ?: "Неизвестная ошибка",
                    onRetry = viewModel::getExpenses,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.LOADING -> LoadingState(modifier = Modifier.padding(innerPadding))
        }
    }
}
