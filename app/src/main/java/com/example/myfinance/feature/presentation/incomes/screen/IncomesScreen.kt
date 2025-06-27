package com.example.myfinance.feature.presentation.incomes.screen

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
import com.example.myfinance.feature.presentation.ScreenState
import com.example.myfinance.feature.presentation.incomes.viewmodel.IncomesViewModel
import com.example.myfinance.ui.components.AppFAB
import com.example.myfinance.ui.components.AppTopBar
import com.example.myfinance.ui.components.ErrorState
import com.example.myfinance.ui.components.LoadingState

/**
 * Экран доходов
 *
 * В зависимости от состояния данных показывает соответствующий экран
 */

@Composable
fun IncomesScreen(onHistoryClicked: () -> Unit) {
    val viewModel: IncomesViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold (
        topBar = {
            AppTopBar(
                title = "Доходы сегодня",
                rightButtonIcon = R.drawable.history,
                rightButtonDescription = "История",
                rightButtonAction = onHistoryClicked
            ) },
        floatingActionButton = { AppFAB() },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        when (state.screenState) {
            ScreenState.SUCCESS -> {
                IncomesContent(
                    incomes = state.incomes,
                    totalSum = state.totalSum,
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
