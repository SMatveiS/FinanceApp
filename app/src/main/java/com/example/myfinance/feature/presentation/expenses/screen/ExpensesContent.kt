package com.example.myfinance.feature.presentation.expenses.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.feature.utils.formatNumber
import com.example.myfinance.ui.components.AppListItem

@Composable
fun ExpensesContent(
    expenses: List<Transaction>,
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
        items(expenses) { expense ->
            ExpenseListItem(expense)
            HorizontalDivider()
        }
    }
}
