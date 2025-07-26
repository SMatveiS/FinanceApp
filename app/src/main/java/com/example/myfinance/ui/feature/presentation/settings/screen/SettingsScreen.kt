package com.example.myfinance.ui.feature.presentation.settings.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myfinance.app.MainActivity
import com.example.myfinance.app.findActivity
import com.example.ui.AppTopBar

@Composable
fun SettingsScreen(
    toPickMainColor: () -> Unit
){

    val context = LocalContext.current
    val activity = context.findActivity() as MainActivity
    val screenComponent = remember {
        activity.activityComponent.screenComponentFactory().create()
    }

    val viewModel = screenComponent.settingsViewModel

    val isDarkThemeEnable by viewModel.darkThemeState.collectAsStateWithLifecycle()

    Scaffold (
        topBar = { AppTopBar(title = "Настройки") },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {

            SwitchThemeItem(isDarkThemeEnable, viewModel::setDarkTheme)
            HorizontalDivider()

            SettingsItem("Основной цвет", toPickMainColor)
            HorizontalDivider()

            SettingsItem("Звуки", {})
            HorizontalDivider()

            SettingsItem("Хаптики", {})
            HorizontalDivider()

            SettingsItem("Синхронизация", {})
            HorizontalDivider()

            SettingsItem("Язык", {})
            HorizontalDivider()

            SettingsItem("О программе", {})
            HorizontalDivider()

        }
    }
}
