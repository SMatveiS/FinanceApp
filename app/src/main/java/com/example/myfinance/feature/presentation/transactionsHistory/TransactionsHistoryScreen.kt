package com.example.myfinance.feature.presentation.transactionsHistory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myfinance.R
import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.feature.utils.formatNumber
import com.example.myfinance.ui.components.AppListItem
import com.example.myfinance.ui.components.AppTopBar
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Currency
import java.util.Locale

@Composable
fun TransactionsHistoryScreen(
    onBackArrowClicked: () -> Unit
) {
    val viewModel: TransactionsHistoryViewModel = viewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val currentState = state) {
        is TransactionsState.Success -> {
            TransactionsHistoryContent(
                transactions = currentState.transactions,
                onBackArrowClicked = onBackArrowClicked
            )
        }
        is TransactionsState.Error -> {
            ErrorState(
                message = currentState.message,
                onRetry = { viewModel.getTransactions() },
                onBack = onBackArrowClicked
            )
        }
        TransactionsState.Loading -> {
            LoadingState(onBack = onBackArrowClicked)
        }
    }
}

@Composable
fun TransactionsHistoryContent(
    transactions: List<Transaction>,
    onBackArrowClicked: () -> Unit
) {
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
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                GetTitles(transactions)
            }
            items(transactions) { transaction ->
                TransactionItem(transaction)
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun GetTitles(
    transactions: List<Transaction>
) {
    val today = LocalDate.now()
    val firstDayOfMonth = today.withDayOfMonth(1)

    val dateFormatter = remember {
        DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
    }

    val totalAmount = remember(transactions) {
        transactions.sumOf { it.amount }
    }

    Column {
        AppListItem(
            leftTitle = "Начало",
            rightTitle = firstDayOfMonth.format(dateFormatter),
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56
        )
        HorizontalDivider()

        AppListItem(
            leftTitle = "Конец",
            rightTitle = today.format(dateFormatter),
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56
        )
        HorizontalDivider()

        AppListItem(
            leftTitle = "Сумма",
            rightTitle = formatNumber(totalAmount),
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56
        )
        HorizontalDivider()
    }
}

@Composable
fun ErrorState(message: String, onRetry: () -> Unit, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Моя история",
                leftButtonIcon = R.drawable.back_arrow,
                leftButtonDescription = "Назад",
                leftButtonAction = onBack
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onRetry) {
                    Text("Повторить попытку")
                }
            }
        }
    }
}

@Composable
fun LoadingState(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Моя история",
                leftButtonIcon = R.drawable.back_arrow,
                leftButtonDescription = "Назад",
                leftButtonAction = onBack
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
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
            Text("Нет данных за период")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text("Загрузить снова")
            }
        }
    }
}
