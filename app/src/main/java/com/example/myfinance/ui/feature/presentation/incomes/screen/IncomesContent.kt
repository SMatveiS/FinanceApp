package com.example.myfinance.ui.feature.presentation.incomes.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.model.Transaction
import com.example.ui.formatNumber
import com.example.ui.AppListItem
import com.example.ui.addCurrency

@Composable
fun IncomesContent(
    incomes: List<Transaction>,
    totalSum: Double,
    currency: String,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    LazyColumn(modifier = modifier) {
        item {
            AppListItem(
                leftTitle = "Всего",
                rightTitle = formatNumber(totalSum).addCurrency(currency),
                itemBackground = MaterialTheme.colorScheme.secondary,
                itemHeight = 56
            )
            HorizontalDivider()
        }
        items(incomes) { income ->
            IncomeListItem(income, currency, onItemClicked)
            HorizontalDivider()
        }
    }
}
