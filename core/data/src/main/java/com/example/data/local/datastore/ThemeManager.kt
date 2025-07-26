package com.example.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(name = "theme")

@Singleton
class ThemeManager @Inject constructor(context: Context) {

    companion object {
        private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
        private val MAIN_COLOR_KEY = intPreferencesKey("main_color")
    }

    private val themeDataStore = context.themeDataStore

    val darkThemeFlow: Flow<Boolean> = themeDataStore.data
        .map { preferences ->
            preferences[DARK_THEME_KEY] ?: false
        }

    suspend fun setDarkTheme(enable: Boolean) {
        themeDataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = enable
        }
    }


    val mainColorFlow: Flow<Int> = themeDataStore.data
        .map { preferences ->
            preferences[MAIN_COLOR_KEY] ?: 0
        }

    suspend fun setMainColor(mainColor: Int) {
        themeDataStore.edit { preferences ->
            preferences[MAIN_COLOR_KEY] = mainColor
        }
    }


}