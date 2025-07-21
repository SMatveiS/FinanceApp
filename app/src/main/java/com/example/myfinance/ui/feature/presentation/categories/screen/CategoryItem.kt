package com.example.myfinance.ui.feature.presentation.categories.screen

import androidx.compose.runtime.Composable
import com.example.model.Category
import com.example.ui.AppListItem

@Composable
fun CategoryItem(category: Category) {
    AppListItem(
        leftTitle = category.name,
        leftIcon = category.emoji
    )
}
