package com.example.myfinance.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.ui.common.FinappFAB
import com.example.myfinance.ui.common.FinappListItem
import com.example.myfinance.ui.common.FinappTopBar

@Composable
fun AccountScreen(){
    Scaffold (
        topBar = {
            FinappTopBar(
                title = "Мой счет",
                actionIcon = R.drawable.edit
            ) },
        floatingActionButton = { FinappFAB() },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            FinappListItem(
                leftTitle = "Баланс",
                rightTitle = "-670 000 ₽",
                leftIcon = "\uD83D\uDCB0",
                rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
                leftIconBackground = MaterialTheme.colorScheme.background,
                listBackground = MaterialTheme.colorScheme.secondary,
                listHeight = 56,
                clickable = true,
                onClick = { /* Действие */ }
            )
            HorizontalDivider()
            FinappListItem(
                leftTitle = "Валюта",
                rightTitle = "₽",
                rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
                listBackground = MaterialTheme.colorScheme.secondary,
                listHeight = 56,
                clickable = true,
                onClick = { /* Действие */ }
            )
        }
    }
}