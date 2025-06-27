package com.example.myfinance.feature.presentation.account.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.feature.utils.formatNumber
import com.example.myfinance.ui.components.AppListItem

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
            rightTitle = formatNumber(balance),
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
            rightTitle = currency,
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56,
            clickable = true,
            onClick = { /* Действие */ }
        )
    }
}
