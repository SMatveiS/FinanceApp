package com.example.myfinance.ui.feature.presentation.settings.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.model.Settings
import com.example.myfinance.R
import com.example.ui.AppListItem

@Composable
fun SettingsItem(settings: Settings) {
    AppListItem(
        leftTitle = settings.name,
        rightIcon = ImageVector.vectorResource(R.drawable.dark_arrow),
        itemHeight = 56,
        clickable = true,
        onClick = { /* Действие */}
    )
}
