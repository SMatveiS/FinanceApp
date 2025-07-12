package com.example.myfinance.ui.feature.presentation.change_transaction.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import com.example.myfinance.domain.model.Category
import com.example.myfinance.ui.common.AppListItem
import com.example.myfinance.ui.common.ErrorState
import com.example.myfinance.ui.common.LoadingState
import com.example.myfinance.ui.feature.presentation.ScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryBottomSheet(
    categories: List<Category>,
    screenState: ScreenState,
    errorMessage: String? = null,
    onDismiss: () -> Unit,
    onCategorySelected: (Category) -> Unit,
    retry: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {

        when (screenState) {
            ScreenState.SUCCESS -> {
                LazyColumn {
                    items(categories) { category ->
                        AppListItem(
                            leftTitle = category.name,
                            leftIcon = category.emoji,
                            clickable = true,
                            onClick = {
                                onCategorySelected(category)
                            }
                        )
                        HorizontalDivider()
                    }
                }
            }

            ScreenState.ERROR -> {
                ErrorState(
                    message = errorMessage ?: "Неизвестная ошибка",
                    onRetry = retry
                )
            }

            ScreenState.LOADING -> LoadingState()
        }
    }
}