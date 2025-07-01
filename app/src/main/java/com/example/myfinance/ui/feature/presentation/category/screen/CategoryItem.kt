package com.example.myfinance.ui.feature.presentation.category.screen

import androidx.compose.runtime.Composable
import com.example.myfinance.domain.model.Category
import com.example.myfinance.ui.common.AppListItem

/**
 * Элемент списка статей
 */

@Composable
fun CategoryItem(category: Category) {
    AppListItem(
        leftTitle = category.name,
        leftIcon = category.emoji
    )
}
