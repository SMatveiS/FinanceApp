package com.example.myfinance.ui.feature.presentation.incomes.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.ui.common.AppListItem
import com.example.myfinance.ui.common.formatNumber

/**
 * Элемент списка доходов
 */

@Composable
fun IncomeListItem(income: Transaction, currency: String) {
    AppListItem(
        leftTitle = income.category.name,
        leftSubtitle = income.comment,
        rightTitle = formatNumber(income.amount, currency),
        leftIcon = income.category.emoji,
        rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
        clickable = true,
        onClick = { /* Действие */}
    )
}
