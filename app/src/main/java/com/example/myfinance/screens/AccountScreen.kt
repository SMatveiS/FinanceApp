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
fun AccountScreen(){
    Column {
        ListItem(
            leftTitle = "Баланс",
            rightTitle = "-670 000 ₽",
            leftIcon = "\uD83D\uDCB0",
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
            leftIconBackground = MaterialTheme.colorScheme.background,
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56,
            clickable = true,
            onClick = { /* Действие */}
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Валюта",
            rightTitle = "₽",
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56,
            clickable = true,
            onClick = { /* Действие */}
        )
    }
}