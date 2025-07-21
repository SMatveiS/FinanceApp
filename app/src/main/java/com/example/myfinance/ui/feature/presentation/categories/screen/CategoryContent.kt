package com.example.myfinance.ui.feature.presentation.categories.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.model.Category

@Composable
fun CategoryContent(
    categories: List<Category>
) {

    if (categories.isNotEmpty()) {
        LazyColumn {
            items(categories) { category ->
                CategoryItem(category)
                HorizontalDivider()
            }
        }
    } else {
        Text(
            "Категории с таким названием не найдены",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
        )
    }
}

