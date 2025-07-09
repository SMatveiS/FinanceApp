package com.example.myfinance.ui.feature.presentation.transactions_history.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.ui.common.AppListItem
import com.example.myfinance.ui.common.addCurrency
import com.example.myfinance.ui.common.formatNumber

@Composable
fun TransactionListItem(transaction: Transaction, currency: String) {
    AppListItem(
        leftTitle = transaction.category.name,
        leftSubtitle = transaction.comment,
        rightTitle = formatNumber(transaction.amount).addCurrency(currency),
        rightSubtitle = transaction.date,
        leftIcon = transaction.category.emoji,
        rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
        clickable = true,
        onClick = { /* Действие */}
    )
}
