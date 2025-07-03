package com.example.myfinance.ui.feature.presentation.incomes.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.ui.common.formatNumber
import com.example.myfinance.ui.common.AppListItem

/**
 * Контент экрана доходов
 */

@Composable
fun IncomesContent(
    incomes: List<Transaction>,
    totalSum: Double,
    currency: String,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier) {
        item {
            AppListItem(
                leftTitle = "Всего",
                rightTitle = formatNumber(totalSum, currency),
                listBackground = MaterialTheme.colorScheme.secondary,
                listHeight = 56
            )
            HorizontalDivider()
        }
        items(incomes) { income ->
            IncomeListItem(income, currency)
            HorizontalDivider()
        }
    }
}
