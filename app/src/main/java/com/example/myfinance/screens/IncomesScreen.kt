package com.example.myfinance.screens

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.ui.common.FinappListItem
import com.example.myfinance.R
import com.example.myfinance.domain.Category
import com.example.myfinance.ui.common.FinappFAB
import com.example.myfinance.ui.common.FinappTopBar

@Composable
fun IncomesScreen(incomes: List<Category>) {
    Scaffold (
        topBar = {
            FinappTopBar(
                title = "Доходы сегодня",
                actionIcon = R.drawable.history
            ) },
        floatingActionButton = { FinappFAB() },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                FinappListItem(
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
}

@Composable
fun IncomeItem(income: Category) {
    FinappListItem(
        leftTitle = income.category,
        leftSubtitle = income.comment,
        rightTitle = formatNumber(income.amount),
        rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
        clickable = true,
        onClick = { /* Действие */}
    )
}