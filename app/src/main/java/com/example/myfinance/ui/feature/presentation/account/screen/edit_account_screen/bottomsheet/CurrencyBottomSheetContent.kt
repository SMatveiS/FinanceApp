package com.example.myfinance.ui.feature.presentation.account.screen.edit_account_screen.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R

@Composable
fun CurrencyBottomSheetContent(
    onCurrencySelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    Column {
        BottomSheetListItem(
            title = "Российский рубль ₽",
            icon = ImageVector.vectorResource(R.drawable.ruble_icon),
            onClick = {
                onCurrencySelected("RUB")
                onDismiss()
            }
        )

        HorizontalDivider()

        BottomSheetListItem(
            title = "Американский доллар $",
            icon = ImageVector.vectorResource(R.drawable.dollar_icon),
            onClick = {
                onCurrencySelected("USD")
                onDismiss()
            }
        )

        HorizontalDivider()

        BottomSheetListItem(
            title = "Евро",
            icon = ImageVector.vectorResource(R.drawable.euro_icon),
            onClick = {
                onCurrencySelected("EUR")
                onDismiss()
            }
        )

        HorizontalDivider()

        BottomSheetListItem(
            title = "Отмена",
            icon = ImageVector.vectorResource(R.drawable.cancel_with_circle),
            textColor = MaterialTheme.colorScheme.onError,
            listBackground = MaterialTheme.colorScheme.error,
            onClick = onDismiss
        )
    }
}