package com.example.myfinance.ui.feature.presentation.incomes.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.domain.model.Transaction
import com.example.ui.AppListItem
import com.example.ui.addCurrency
import com.example.ui.formatNumber

@Composable
fun IncomeListItem(
    income: Transaction,
    currency: String,
    onClick: (Int) -> Unit
) {

    AppListItem(
        leftTitle = income.category.name,
        leftSubtitle = income.comment,
        rightTitle = formatNumber(income.amount).addCurrency(currency),
        leftIcon = income.category.emoji,
        rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
        clickable = true,
        onClick = { onClick(income.id) }
    )
}
