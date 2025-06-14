package com.example.myfinance.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.myfinance.Article
import com.example.myfinance.ListItem
import com.example.myfinance.R

@Composable
fun ArticlesScreen(articles: List<Article>){
    LazyColumn {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .padding(start = 4.dp)
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(
                            "Найти статью",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    trailingIcon = {
                        Icon(
                            ImageVector.vectorResource(R.drawable.search_icon),
                            contentDescription = "Найти",
                            modifier = Modifier.width(24.dp)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            HorizontalDivider()
        }
        items(articles) { article ->
            ArticleItem(article)
            HorizontalDivider()
        }
    }
}

@Composable
fun ArticleItem(article: Article) {
    ListItem(
        leftTitle = article.name,
        leftIcon = article.emoji
    )
}