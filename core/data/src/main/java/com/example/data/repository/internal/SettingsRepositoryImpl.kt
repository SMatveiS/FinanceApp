package com.example.data.repository.internal

import com.example.data.local.datastore.SettingsManager
import com.example.data.repository.external.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsManager: SettingsManager
): SettingsRepository {

    override val darkThemeFlow = settingsManager.darkThemeFlow

    override suspend fun setDarkTheme(isDarkThemeEnable: Boolean) = withContext(Dispatchers.IO) {
        settingsManager.setDarkTheme(enable = isDarkThemeEnable)
    }


    override val mainColorFlow = settingsManager.mainColorFlow

    override suspend fun setMainColor(mainColor: Int) = withContext(Dispatchers.IO) {
        settingsManager.setMainColor(mainColor)
    }


    override val syncFreqFlow = settingsManager.syncFreqFlow

    override suspend fun setSyncFreq(syncFreq: Int) = withContext(Dispatchers.IO) {
        settingsManager.setSyncFreq(syncFreq)
    }
}