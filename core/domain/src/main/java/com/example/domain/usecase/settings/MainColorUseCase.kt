package com.example.domain.usecase.settings

import com.example.data.repository.external.SettingsRepository
import javax.inject.Inject

class MainColorUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    val mainColorFlow = settingsRepository.mainColorFlow

    suspend fun setMainColor(mainColor: Int) = settingsRepository.setMainColor(mainColor)
}