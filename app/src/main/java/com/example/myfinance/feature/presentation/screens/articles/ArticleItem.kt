package com.example.myfinance.feature.presentation.screens.articles

import androidx.compose.runtime.Composable
import com.example.myfinance.feature.domain.Article
import com.example.myfinance.ui.components.AppListItem

@Composable
fun ArticleItem(article: Article) {
    AppListItem(
        leftTitle = article.name,
        leftIcon = article.emoji
    )
}