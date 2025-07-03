package com.example.myfinance.ui.feature.presentation.account.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.ui.common.AppListItem
import com.example.myfinance.ui.common.formatNumber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccountContent(
    balance: Double,
    currency: String,
    modifier: Modifier = Modifier
) {

    var isSheetOpen by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier) {
        AppListItem(
            leftTitle = "Баланс",
            rightTitle = formatNumber(balance, currency),
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
            onClick = { isSheetOpen = true }
        )
    }

    val sheetState = rememberModalBottomSheetState()
    val onDismiss = { isSheetOpen = false}

    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismiss
        ) {

            Column {
                BottomSheetListItem(
                    title = "Российский рубль ₽",
                    icon = ImageVector.vectorResource(R.drawable.ruble_icon),
                    onClick = onDismiss
                )

                HorizontalDivider()

                BottomSheetListItem(
                    title = "Американский доллар $",
                    icon = ImageVector.vectorResource(R.drawable.dollar_icon),
                    onClick = onDismiss
                )

                HorizontalDivider()

                BottomSheetListItem(
                    title = "Евро",
                    icon = ImageVector.vectorResource(R.drawable.euro_icon),
                    onClick = onDismiss
                )

                HorizontalDivider()

                BottomSheetListItem(
                    title = "Отмена",
                    icon = ImageVector.vectorResource(R.drawable.cancel_icon),
                    textColor = MaterialTheme.colorScheme.onError,
                    listBackground = MaterialTheme.colorScheme.error,
                    onClick = onDismiss
                )
            }
        }
    }
}