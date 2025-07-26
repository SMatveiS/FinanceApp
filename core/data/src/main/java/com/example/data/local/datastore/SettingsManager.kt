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

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class SettingsManager @Inject constructor(context: Context) {

    companion object {
        private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
        private val MAIN_COLOR_KEY = intPreferencesKey("main_color")
        private val SYNC_FREQ_KEY = intPreferencesKey("sync_frequency")
    }

    private val settingsDataStore = context.settingsDataStore


    val darkThemeFlow: Flow<Boolean> = settingsDataStore.data
        .map { preferences ->
            preferences[DARK_THEME_KEY] ?: false
        }

    suspend fun setDarkTheme(enable: Boolean) {
        settingsDataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = enable
        }
    }


    val mainColorFlow: Flow<Int> = settingsDataStore.data
        .map { preferences ->
            preferences[MAIN_COLOR_KEY] ?: 0
        }

    suspend fun setMainColor(mainColor: Int) {
        settingsDataStore.edit { preferences ->
            preferences[MAIN_COLOR_KEY] = mainColor
        }
    }


    val syncFreqFlow: Flow<Int> = settingsDataStore.data
        .map { preferences ->
            preferences[SYNC_FREQ_KEY] ?: 5
        }

    suspend fun setSyncFreq(syncFreq: Int) {
        settingsDataStore.edit { preferences ->
            println("Новое значение: $syncFreq")
            preferences[SYNC_FREQ_KEY] = syncFreq
        }
    }
}
