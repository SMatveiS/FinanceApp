package com.example.myfinance.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.ui.common.FinappListItem
import com.example.myfinance.R
import com.example.myfinance.domain.Transaction

@Composable
fun TransactionHistoryScreen(categories: List<Transaction>) {
    LazyColumn {
        item {
            FinappListItem(
                leftTitle = "Начало",
                rightTitle = "436 558 ₽",
                listBackground = MaterialTheme.colorScheme.secondary,
                listHeight = 56
            )
            HorizontalDivider()
            FinappListItem(
                leftTitle = "Конец",
                rightTitle = "436 558 ₽",
                listBackground = MaterialTheme.colorScheme.secondary,
                listHeight = 56
            )
            HorizontalDivider()
            FinappListItem(
                leftTitle = "Сумма",
                rightTitle = "436 558 ₽",
                listBackground = MaterialTheme.colorScheme.secondary,
                listHeight = 56
            )
        }
        items(categories) { transaction ->
            TransactionItem(transaction)
            HorizontalDivider()
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    FinappListItem(
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