package com.example.myfinance.feature.presentation.screens.incomes

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.feature.domain.Category
import com.example.myfinance.ui.components.AppListItem
import com.example.myfinance.ui.utils.formatNumber

@Composable
fun IncomeItem(income: Category) {
    AppListItem(
        leftTitle = income.category,
        leftSubtitle = income.comment,
        rightTitle = formatNumber(income.amount),
        rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
        clickable = true,
        onClick = { /* Действие */}
    )
}