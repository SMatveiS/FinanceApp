package com.example.myfinance.ui.feature.presentation.change_transaction.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfinance.R
import com.example.myfinance.domain.model.Category
import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.ui.common.AppListItem
import com.example.myfinance.ui.common.getCurrencySymbol
import com.example.myfinance.ui.feature.presentation.ScreenState
import com.example.myfinance.ui.common.EditTextListItem

@Composable
fun ChangeTransactionContent(
    transaction: Transaction?,
    date: String,
    time: String,
    categories: List<Category>,
    categoriesState: ScreenState,
    getCategories: () -> Unit,
    selectCategory: (Category) -> Unit,
    onDatePickerOpen: () -> Unit,
    onTimePickerOpen: () -> Unit,
    onSumChanged: (Double) -> Unit,
    onCommentChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    categoriesErrorMessage: String? = null,
    account: String

) {

    var sum by rememberSaveable { mutableStateOf(transaction?.amount.toString()) }
    var comment by rememberSaveable { mutableStateOf(transaction?.comment ?: "") }
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier) {
        AppListItem(
            leftTitle = "Счет",
            rightTitle = account,
            itemHeight = 70
        )

        HorizontalDivider()

        AppListItem(
            leftTitle = "Статья",
            rightTitle = transaction?.category?.name,
            rightIcon = ImageVector.vectorResource(R.drawable.light_arrow),
            itemHeight = 70,
            clickable = true,
            onClick = {
                getCategories()
                isSheetOpen = true
            }
        )

        HorizontalDivider()

        EditTextListItem(
            title = "Сумма",
            editTextInitialValue = sum,
            onValueChange = { newValue ->
                if (newValue.matches(Regex("^\\d*([.,]\\d{0,2})?$"))) {
                    sum = newValue
                    newValue.replace(",", ".").toDoubleOrNull()?.let { value ->
                        onSumChanged(value)
                    }
                }
            },
            trailText = getCurrencySymbol(transaction?.currency ?: "RUB"),
            itemHeight = 70
        )

        HorizontalDivider()

        AppListItem(
            leftTitle = "Дата",
            rightTitle = date,
            itemHeight = 70,
            clickable = true,
            onClick = onDatePickerOpen
        )

        HorizontalDivider()

        AppListItem(
            leftTitle = "Время",
            rightTitle = time,
            itemHeight = 70,
            clickable = true,
            onClick = onTimePickerOpen
        )

        HorizontalDivider()

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            TextField(
                value = comment,
                onValueChange = { newValue ->
                    comment = newValue
                    onCommentChanged(newValue)
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                placeholder = {
                    Text(
                        text = "Комментарий",
                        color = Color.Gray
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

        HorizontalDivider()
    }

    if (isSheetOpen) {
        CategoryBottomSheet(
            onDismiss = { isSheetOpen = false },
            onCategorySelected = { category ->
                selectCategory(category)
                isSheetOpen = false
            },
            categories = categories,
            screenState = categoriesState,
            errorMessage = categoriesErrorMessage,
            retry = getCategories
        )
    }
}