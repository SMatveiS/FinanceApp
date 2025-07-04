package com.example.myfinance.ui.feature.presentation.transactionsHistory.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.ui.common.addCurrency
import com.example.myfinance.ui.common.formatNumber
import java.time.LocalDate

/**
 * Контент экрана истории транзакций
 */

@Composable
fun TransactionsHistoryContent(
    transactions: List<Transaction>,
    startDate: LocalDate,
    endDate: LocalDate,
    totalSum: Double,
    currency: String,
    onStartDatePickerOpen: () -> Unit,
    onEndDatePickerOpen: () -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier) {
        item {
            Column {
                TransactionHistoryTitles(
                    startDate = startDate,
                    endDate = endDate,
                    totalSum = formatNumber(totalSum).addCurrency(currency),
                    onStartDatePickerOpen = onStartDatePickerOpen,
                    onEndDatePickerOpen = onEndDatePickerOpen
                )

                HorizontalDivider()
            }
        }

        items(transactions) { transaction ->
            TransactionListItem(transaction, currency)
            HorizontalDivider()
        }
    }
}
