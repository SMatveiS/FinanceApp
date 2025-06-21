package com.example.myfinance.feature.presentation.transactionsHistory

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.ui.components.AppListItem
import com.example.myfinance.feature.utils.formatNumber

@Composable
fun TransactionItem(transaction: Transaction) {
    AppListItem(
        leftTitle = transaction.category,
        leftSubtitle = transaction.comment,
        rightTitle = formatNumber(transaction.amount),
        rightSubtitle = transaction.date,
        leftIcon = transaction.emoji,
        rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
        clickable = true,
        onClick = { /* Действие */}
    )
}