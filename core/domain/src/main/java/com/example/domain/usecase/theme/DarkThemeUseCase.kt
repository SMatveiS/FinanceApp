package com.example.domain.usecase.theme

import com.example.data.local.datastore.ThemeManager
import javax.inject.Inject

class DarkThemeUseCase @Inject constructor(
    private val themeManager: ThemeManager
) {

    val darkThemeFlow = themeManager.darkThemeFlow

    suspend fun setDarkTheme(isDarkThemeEnable: Boolean) =
        themeManager.setDarkTheme(isDarkThemeEnable)
}