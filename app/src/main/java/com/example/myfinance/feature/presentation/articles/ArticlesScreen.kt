package com.example.myfinance.feature.presentation.articles

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfinance.feature.domain.model.Article
import com.example.myfinance.ui.components.AppTopBar

@Composable
fun ArticlesScreen(articles: List<Article>){
    Scaffold (
        topBar = { AppTopBar(title = "Мои статьи") },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                SearchField()
                HorizontalDivider()
            }
            items(articles) { article ->
                ArticleItem(article)
                HorizontalDivider()
            }
        }
    }
}
