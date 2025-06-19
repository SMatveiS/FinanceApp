package com.example.myfinance.feature.presentation.screens.account

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
import com.example.myfinance.ui.components.AppFAB
import com.example.myfinance.ui.components.AppListItem
import com.example.myfinance.ui.components.AppTopBar

@Composable
fun AccountScreen(){
    Scaffold (
        topBar = {
            AppTopBar(
                title = "Мой счет",
                rightButtonIcon = R.drawable.edit,
                rightButtonDescription = "Изменить"
            ) },
        floatingActionButton = { AppFAB() },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            AppListItem(
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
            AppListItem(
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