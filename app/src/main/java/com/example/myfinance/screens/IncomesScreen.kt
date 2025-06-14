package com.example.myfinance.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.ListItem
import com.example.myfinance.R

@Composable
fun IncomesScreen() {
    Column {
        ListItem(
            leftTitle = "Всего",
            rightTitle = "600 000 ₽",
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Зарплата",
            rightTitle = "500 000 ₽",
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
            listHeight = 72
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Подработка",
            rightTitle = "100 000 ₽",
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
            listHeight = 72
        )
        HorizontalDivider()
    }
}