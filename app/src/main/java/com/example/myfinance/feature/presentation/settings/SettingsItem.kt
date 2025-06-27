package com.example.myfinance.feature.presentation.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myfinance.R
import com.example.myfinance.feature.domain.model.Settings
import com.example.myfinance.ui.components.AppListItem

@Composable
fun SettingsItem(settings: Settings) {
    AppListItem(
        leftTitle = settings.name,
        rightIcon = ImageVector.vectorResource(R.drawable.dark_arrow),
        listHeight = 56,
        clickable = true,
        onClick = { /* Действие */}
    )
}
