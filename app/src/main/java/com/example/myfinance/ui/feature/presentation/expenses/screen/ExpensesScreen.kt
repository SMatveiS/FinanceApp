package com.example.myfinance.ui.feature.presentation.expenses.screen

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
import com.example.ui.AppFAB
import com.example.ui.AppTopBar
import com.example.ui.screenstate.ErrorState
import com.example.ui.screenstate.LoadingState
import com.example.ui.screenstate.ScreenState

@Composable
fun ExpensesScreen(
    onHistoryClicked: () -> Unit,
    onFabClicked: () -> Unit,
    onItemClicked: (Int) -> Unit
) {

    val context = LocalContext.current
    val activity = context.findActivity() as MainActivity
    val screenComponent = remember {
        activity.activityComponent.screenComponentFactory().create()
    }

    val viewModel = screenComponent.expenseViewModel

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
                    onItemClicked = onItemClicked,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.ERROR -> {
                ErrorState(
                    message = state.errorMessage,
                    onRetry = viewModel::getExpenses,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            ScreenState.LOADING -> LoadingState(modifier = Modifier.padding(innerPadding))
        }
    }
}
