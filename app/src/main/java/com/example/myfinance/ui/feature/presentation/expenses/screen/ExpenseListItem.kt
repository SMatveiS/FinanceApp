package com.example.myfinance.ui.feature.presentation.expenses.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.ui.common.AppListItem
import com.example.myfinance.ui.common.addCurrency
import com.example.myfinance.ui.common.formatNumber

@Composable
fun ExpenseListItem(
    expense: Transaction,
    currency: String,
    onClick: (Int) -> Unit
) {

    AppListItem(
        leftTitle = expense.category.name,
        leftSubtitle = expense.comment,
        rightTitle = formatNumber(expense.amount).addCurrency(currency),
        leftIcon = expense.category.emoji,
        rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
        clickable = true,
        onClick = { onClick(expense.id) }
    )
}
