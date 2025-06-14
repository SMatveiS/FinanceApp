package com.example.myfinance.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.Income
import com.example.myfinance.ListItem
import com.example.myfinance.R

@Composable
fun IncomesScreen(incomes: List<Income>) {
    LazyColumn {
        item {
            ListItem(
                leftTitle = "Всего",
                rightTitle = "600 000 ₽",
                listBackground = MaterialTheme.colorScheme.secondary,
                listHeight = 56
            )
            HorizontalDivider()
        }
        items(incomes) { income ->
            IncomeItem(income)
            HorizontalDivider()
        }
    }
}

@Composable
fun IncomeItem(income: Income) {
    ListItem(
        leftTitle = income.category,
        leftSubtitle = income.comment,
        rightTitle = formatNumber(income.amount),
        rightIcon = ImageVector.vectorResource(R.drawable.light_arrow)
    )
}