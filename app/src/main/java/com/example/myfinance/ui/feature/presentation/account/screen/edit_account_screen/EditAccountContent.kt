package com.example.myfinance.ui.feature.presentation.account.screen.edit_account_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import com.example.myfinance.domain.model.Account
import com.example.myfinance.ui.common.AppListItem
import com.example.myfinance.ui.feature.presentation.account.screen.edit_account_screen.bottomsheet.BottomSheetContent
import com.example.myfinance.ui.common.getCurrencySymbol

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccountContent(
    account: Account?,
    onNameChanged: (String) -> Unit,
    onBalanceChanged: (Double) -> Unit,
    onCurrencySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var balance by rememberSaveable { mutableStateOf(account?.balance?.toString() ?: "0") }

    Column(modifier = modifier) {

        EditTextListItem(
            leftTitle = "Название счёта",
            editTextInitialValue = account?.name ?: "Баланс",
            onValueChange = onNameChanged,
            emoji = "\uD83D\uDCB0"
        )

        HorizontalDivider()

        EditTextListItem(
            leftTitle = "Баланс",
            editTextInitialValue = balance,
            onValueChange = { newValue ->
                    if (newValue.matches(Regex("^\\d*([.,]\\d{0,2})?$"))) {
                        balance = newValue
                        newValue.replace(",", ".").toDoubleOrNull()?.let { value ->
                            onBalanceChanged(value)
                        }
                    }
                },
            trailText = getCurrencySymbol(account?.currency ?: "RUB")
        )

        HorizontalDivider()

        AppListItem(
            leftTitle = "Валюта",
            rightTitle = getCurrencySymbol(account?.currency ?: "RUB"),
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
            listHeight = 56,
            clickable = true,
            onClick = { isSheetOpen = true }
        )

        HorizontalDivider()
    }

    val sheetState = rememberModalBottomSheetState()
    val onDismiss = { isSheetOpen = false}

    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismiss
        ) {

            BottomSheetContent(
                onCurrencySelected = onCurrencySelected,
                onDismiss = onDismiss
            )
        }
    }
}
