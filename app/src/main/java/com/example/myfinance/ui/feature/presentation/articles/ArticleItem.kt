package com.example.myfinance.ui.feature.presentation.articles

import androidx.compose.runtime.Composable
import com.example.myfinance.domain.model.Article
import com.example.myfinance.ui.common.AppListItem

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
