package com.example.myfinance.ui.feature.presentation.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.theme.DarkThemeUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val darkThemeUseCase: DarkThemeUseCase
): ViewModel() {

    val darkThemeState = darkThemeUseCase.darkThemeFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = false
    )

    fun setDarkTheme(isDarkThemeEnable: Boolean) = viewModelScope.launch {
        darkThemeUseCase.setDarkTheme(isDarkThemeEnable)
    }

}