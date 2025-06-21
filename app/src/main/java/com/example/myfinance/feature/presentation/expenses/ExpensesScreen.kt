package com.example.myfinance.feature.presentation.expenses

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import com.example.myfinance.R
import com.example.myfinance.feature.domain.model.Category
import com.example.myfinance.ui.components.AppFAB
import com.example.myfinance.ui.components.AppListItem
import com.example.myfinance.ui.components.AppTopBar


@Composable
fun ExpensesScreen(onHistoryClicked: () -> Unit) {

    val viewModel: ExpenseViewModel = viewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()


    when (val currentState = state) {
        is ExpensesState.Success -> {
            ExpensesContent(
                expenses = currentState.expenses,
                onHistoryClicked = onHistoryClicked,
            )
        }
        is ExpensesState.Error -> {
            ErrorState(
                message = currentState.message,
                onRetry = { viewModel.getExpenses() }
            )
        }
        ExpensesState.Loading -> {
            LoadingState()
        }
    }
}

@Composable
fun ExpensesContent(expenses: List<Category>, onHistoryClicked: () -> Unit) {
    Scaffold (
        topBar = {
            AppTopBar(
                title = "Расходы сегодня",
                rightButtonIcon = R.drawable.history,
                rightButtonDescription = "История",
                rightButtonAction = onHistoryClicked
            ) },
        floatingActionButton = { AppFAB() },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                AppListItem(
                    leftTitle = "Всего",
                    rightTitle = "436 558 ₽",
                    listBackground = MaterialTheme.colorScheme.secondary,
                    listHeight = 56
                )
                HorizontalDivider()
            }
            items(expenses) { expense ->
                ExpenseItem(expense)
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorState(message: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text("Повторить попытку")
            }
        }
    }
}

@Composable
fun EmptyState(onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Данные не найдены")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text("Загрузить снова")
            }
        }
    }
}

