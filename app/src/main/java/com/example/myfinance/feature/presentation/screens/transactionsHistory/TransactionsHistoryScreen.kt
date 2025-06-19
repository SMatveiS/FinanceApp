package com.example.myfinance.feature.presentation.screens.transactionsHistory

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfinance.R
import com.example.myfinance.feature.domain.Transaction
import com.example.myfinance.ui.components.AppListItem
import com.example.myfinance.ui.components.AppTopBar

@Composable
fun TransactionsHistoryScreen(
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
                AppListItem(
                    leftTitle = "Начало",
                    rightTitle = "436 558 ₽",
                    listBackground = MaterialTheme.colorScheme.secondary,
                    listHeight = 56
                )
                HorizontalDivider()
                AppListItem(
                    leftTitle = "Конец",
                    rightTitle = "436 558 ₽",
                    listBackground = MaterialTheme.colorScheme.secondary,
                    listHeight = 56
                )
                HorizontalDivider()
                AppListItem(
                    leftTitle = "Сумма",
                    rightTitle = "436 558 ₽",
                    listBackground = MaterialTheme.colorScheme.secondary,
                    listHeight = 56
                )
            }
            items(transactions) { transaction ->
                TransactionItem(transaction)
                HorizontalDivider()
            }
        }
    }
}