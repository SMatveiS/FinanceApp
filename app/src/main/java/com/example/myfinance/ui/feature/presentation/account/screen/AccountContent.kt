package com.example.myfinance.ui.feature.presentation.account.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfinance.ui.common.formatNumber
import com.example.myfinance.ui.common.AppListItem

/**
 * Контент экрана счёта
 */

@Composable
fun AccountContent(
    balance: Double,
    currency: String,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        AppListItem(
            leftTitle = "Баланс",
            rightTitle = formatNumber(balance, currency),
            leftIcon = "\uD83D\uDCB0",
            leftIconBackground = MaterialTheme.colorScheme.background,
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56
        )

        HorizontalDivider()

        AppListItem(
            leftTitle = "Валюта",
            rightTitle = currency,
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56
        )
    }
}
