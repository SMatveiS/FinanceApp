package com.example.myfinance.ui.feature.presentation.category.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfinance.domain.model.Category

@Composable
fun CategoryContent(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item {
            SearchField()
            HorizontalDivider()
        }
        items(categories) { category ->
            CategoryItem(category)
            HorizontalDivider()
        }
    }
}