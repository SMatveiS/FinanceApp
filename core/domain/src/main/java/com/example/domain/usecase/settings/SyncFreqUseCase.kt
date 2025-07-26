package com.example.domain.usecase.settings

import com.example.data.repository.external.SettingsRepository
import javax.inject.Inject

class SyncFreqUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    val syncFreqFlow = settingsRepository.syncFreqFlow

    suspend fun setSyncFreq(syncFreq: Int) = settingsRepository.setSyncFreq(syncFreq)
}