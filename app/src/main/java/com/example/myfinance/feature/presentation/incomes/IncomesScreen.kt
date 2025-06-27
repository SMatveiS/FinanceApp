package com.example.myfinance.feature.presentation.incomes

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
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myfinance.ui.components.AppListItem
import com.example.myfinance.R
import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.feature.presentation.transactionsHistory.ScreenState
import com.example.myfinance.feature.utils.formatNumber
import com.example.myfinance.ui.components.AppFAB
import com.example.myfinance.ui.components.AppTopBar
import com.example.myfinance.ui.components.ErrorState
import com.example.myfinance.ui.components.LoadingState

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
                    incomes = state.expenses,
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

@Composable
fun IncomesContent(
    incomes: List<Transaction>,
    totalSum: Double,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier) {
        item {
            AppListItem(
                leftTitle = "Всего",
                rightTitle = formatNumber(totalSum),
                listBackground = MaterialTheme.colorScheme.secondary,
                listHeight = 56
            )
            HorizontalDivider()
        }
        items(incomes) { income ->
            IncomeListItem(income)
            HorizontalDivider()
        }
    }
}