package com.example.domain.usecase.settings

import com.example.data.repository.external.SettingsRepository
import javax.inject.Inject

class DarkThemeUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    val darkThemeFlow = settingsRepository.darkThemeFlow

    suspend fun setDarkTheme(isDarkThemeEnable: Boolean) =
        settingsRepository.setDarkTheme(isDarkThemeEnable)
}