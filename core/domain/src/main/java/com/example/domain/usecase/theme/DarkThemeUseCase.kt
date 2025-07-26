package com.example.domain.usecase.theme

import com.example.data.repository.external.ThemeRepository
import javax.inject.Inject

class DarkThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {

    val darkThemeFlow = themeRepository.darkThemeFlow

    suspend fun setDarkTheme(isDarkThemeEnable: Boolean) =
        themeRepository.setDarkTheme(isDarkThemeEnable)
}