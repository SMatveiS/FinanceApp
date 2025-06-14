package com.example.myfinance.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.myfinance.ListItem

@Composable
fun AccountScreen(){
    Column {
        ListItem(
            leftTitle = "Баланс",
            rightTitle = "-670 000 ₽",
            leftIcon = "\uD83D\uDCB0",
            leftIconBackground = MaterialTheme.colorScheme.background,
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56
        )
        HorizontalDivider()
        ListItem(
            leftTitle = "Валюта",
            rightTitle = "₽",
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56
        )
    }
}