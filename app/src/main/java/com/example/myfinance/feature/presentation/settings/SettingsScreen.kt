package com.example.myfinance.feature.presentation.settings

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfinance.feature.domain.model.Settings
import com.example.myfinance.ui.components.AppTopBar

/**
 * Экран настроек
 */

@Composable
fun SettingsScreen(settings: List<Settings>){
    Scaffold (
        topBar = { AppTopBar(title = "Настройки") },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                SwitchThemeItem()
                HorizontalDivider()
            }
            items(settings) { item ->
                SettingsItem(item)
                HorizontalDivider()
            }
        }
    }
}
