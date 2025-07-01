package com.example.myfinance.ui.feature.presentation.transactionsHistory.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.ui.common.AppListItem
import com.example.myfinance.ui.common.formatNumber

/**
 * Элемент списка истории транзакций
 */

@Composable
fun TransactionListItem(transaction: Transaction) {
    AppListItem(
        leftTitle = transaction.category.name,
        leftSubtitle = transaction.comment,
        rightTitle = formatNumber(transaction.amount),
        rightSubtitle = transaction.date,
        leftIcon = transaction.category.emoji,
        rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
        clickable = true,
        onClick = { /* Действие */}
    )
}
