package com.example.myfinance.ui.feature.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfinance.BuildConfig
import com.example.myfinance.R
import com.example.ui.AppListItem
import com.example.ui.AppTopBar
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun AppInfoScreen(
    returnToPreviousScreen: () -> Unit
) {
    Scaffold (
        topBar = { AppTopBar(
            title = "О приложении",
            leftButtonIcon = R.drawable.back_arrow,
            leftButtonDescription = "Назад",
            leftButtonAction = returnToPreviousScreen
        ) },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            AppListItem(
                leftTitle = "Версия приложения",
                rightTitle = BuildConfig.VERSION_CODE.toString()
            )
            HorizontalDivider()

            // Дата сборки проекта
            AppListItem(
                leftTitle = "Дата последнего обновления",
                rightTitle = formatTimeMillisToDate(BuildConfig.BUILD_TIME)
            )
            HorizontalDivider()
        }
    }
}

fun formatTimeMillisToDate(timeInMillis: Long): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")
    return Instant.ofEpochMilli(timeInMillis)
        .atZone(ZoneId.systemDefault())
        .format(formatter)
}