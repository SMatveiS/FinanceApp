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
import androidx.compose.ui.Modifier
import com.example.myfinance.ui.components.AppListItem
import com.example.myfinance.R
import com.example.myfinance.feature.domain.model.Category
import com.example.myfinance.ui.components.AppFAB
import com.example.myfinance.ui.components.AppTopBar

@Composable
fun IncomesScreen(incomes: List<Category>, onHistoryClicked: () -> Unit) {
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
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                AppListItem(
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