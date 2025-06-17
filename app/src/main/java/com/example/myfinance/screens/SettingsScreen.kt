package com.example.myfinance.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfinance.ListItem
import com.example.myfinance.R
import com.example.myfinance.domain.Settings

@Composable
fun SettingsScreen(settings: List<Settings>){
    LazyColumn {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(16.dp)
            ) {
                Text(
                    "Темная тема",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Switch(
                    checked = false,
                    onCheckedChange = {}
                )
            }
            HorizontalDivider()
        }
        items(settings) { item ->
            SettingsItem(item)
            HorizontalDivider()
        }
    }
}

@Composable
fun SettingsItem(settings: Settings) {
    ListItem(
        leftTitle = settings.name,
        rightIcon = ImageVector.vectorResource(R.drawable.dark_arrow),
        listHeight = 56,
        clickable = true,
        onClick = { /* Действие */}
    )
}