package com.example.data.repository.internal

import com.example.data.local.datastore.ThemeManager
import com.example.data.repository.external.ThemeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val themeManager: ThemeManager
): ThemeRepository {

    override val darkThemeFlow = themeManager.darkThemeFlow

    override suspend fun setDarkTheme(isDarkThemeEnable: Boolean) = withContext(Dispatchers.IO) {
        themeManager.setDarkTheme(enable = isDarkThemeEnable)
    }


    override val mainColorFlow = themeManager.mainColorFlow

    override suspend fun setMainColor(mainColor: Int) = withContext(Dispatchers.IO) {
        themeManager.setMainColor(mainColor)
    }
}