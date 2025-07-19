package com.example.myfinance.ui.feature.presentation.analysis.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.ui.common.AppListItem
import com.example.myfinance.ui.common.addCurrency
import com.example.myfinance.ui.common.formatNumber
import com.example.myfinance.domain.model.CategoryStatistic

@Composable
fun CategoryStatisticListItem(
    categoryStatistic: CategoryStatistic,
    currency: String
) {
    AppListItem(
        leftTitle = categoryStatistic.category.name,
        leftIcon = categoryStatistic.category.emoji,
        rightTitle = ((categoryStatistic.proportion * 100).toInt() / 100.0).toString() + " %",
        rightSubtitle = formatNumber(categoryStatistic.amount).addCurrency(currency),
        rightSubtitleSize = 16,
        rightIcon = ImageVector.vectorResource(R.drawable.light_arrow)
    )
}