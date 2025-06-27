package com.example.myfinance.feature.presentation.articles

import androidx.compose.runtime.Composable
import com.example.myfinance.feature.domain.model.Article
import com.example.myfinance.ui.components.AppListItem

/**
 * Элемент списка статей
 */

@Composable
fun ArticleItem(article: Article) {
    AppListItem(
        leftTitle = article.name,
        leftIcon = article.emoji
    )
}
