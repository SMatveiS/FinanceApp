package com.example.myfinance.feature.presentation.expenses.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.ui.components.AppListItem
import com.example.myfinance.feature.utils.formatNumber

@Composable
fun ExpenseListItem(expense: Transaction) {
    AppListItem(
        leftTitle = expense.category.name,
        leftSubtitle = expense.comment,
        rightTitle = formatNumber(expense.amount),
        leftIcon = expense.category.emoji,
        rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
        clickable = true,
        onClick = { /* Действие */}
    )
}
