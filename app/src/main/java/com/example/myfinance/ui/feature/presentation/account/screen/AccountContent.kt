package com.example.myfinance.ui.feature.presentation.account.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfinance.domain.model.Account
import com.example.myfinance.ui.common.formatNumber
import com.example.myfinance.ui.common.AppListItem
import com.example.myfinance.ui.common.addCurrency
import com.example.myfinance.ui.common.getCurrencySymbol

@Composable
fun AccountContent(
    account: Account?,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        AppListItem(
            leftTitle = account?.name ?: "Баланс",
            rightTitle = formatNumber(account?.balance ?: 0.0).addCurrency(account?.currency ?: "RUB"),
            leftIcon = "\uD83D\uDCB0",
            leftIconBackground = MaterialTheme.colorScheme.background,
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56
        )

        HorizontalDivider()

        AppListItem(
            leftTitle = "Валюта",
            rightTitle = getCurrencySymbol(account?.currency ?: "RUB"),
            listBackground = MaterialTheme.colorScheme.secondary,
            listHeight = 56
        )
    }
}
