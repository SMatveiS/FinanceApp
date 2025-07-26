package com.example.domain.usecase.theme

import com.example.data.repository.external.ThemeRepository
import javax.inject.Inject

class MainColorUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {

    val mainColorFlow = themeRepository.mainColorFlow

    suspend fun setMainColor(mainColor: Int) =
        themeRepository.setMainColor(mainColor)
}