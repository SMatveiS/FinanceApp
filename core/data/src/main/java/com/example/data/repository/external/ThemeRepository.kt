package com.example.data.repository.external

import kotlinx.coroutines.flow.Flow

interface ThemeRepository {

    val darkThemeFlow: Flow<Boolean>

    suspend fun setDarkTheme(isDarkThemeEnable: Boolean)


    val mainColorFlow: Flow<Int>

    suspend fun setMainColor(mainColor: Int)
}