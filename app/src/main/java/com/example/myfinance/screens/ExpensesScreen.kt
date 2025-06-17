package com.example.myfinance.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.domain.Expense
import com.example.myfinance.R
import com.example.myfinance.ListItem
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

@Composable
fun ExpensesScreen(expenses: List<Expense>) {
    LazyColumn {
        item {
            ListItem(
                leftTitle = "Всего",
                rightTitle = "436 558 ₽",
                listBackground = MaterialTheme.colorScheme.secondary,
                listHeight = 56
            )
            HorizontalDivider()
        }
        items(expenses) { expense ->
            ExpenseItem(expense)
            HorizontalDivider()
        }
    }
}

@Composable
fun ExpenseItem(expense: Expense) {
    ListItem(
        leftTitle = expense.category,
        leftSubtitle = expense.comment,
        rightTitle = formatNumber(expense.amount),
        leftIcon = expense.emoji,
        rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
        clickable = true,
        onClick = { /* Действие */}
    )
}

fun formatNumber(number: Int): String {
    val symbols = DecimalFormatSymbols().apply {
        groupingSeparator = ' '
    }
    val formatter = DecimalFormat("#,###", symbols)
    return formatter.format(number) + "₽"
}

