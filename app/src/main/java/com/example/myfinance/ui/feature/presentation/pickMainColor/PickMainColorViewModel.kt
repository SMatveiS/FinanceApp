package com.example.myfinance.ui.feature.presentation.pickMainColor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.settings.MainColorUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PickMainColorViewModel @Inject constructor(
    private val mainColorUseCase: MainColorUseCase
): ViewModel() {

    fun setGreenMainColor() = viewModelScope.launch {
        mainColorUseCase.setMainColor(0)
    }

    fun setBlueMainColor() = viewModelScope.launch {
        mainColorUseCase.setMainColor(1)
    }

    fun setPurpleMainColor() = viewModelScope.launch {
        mainColorUseCase.setMainColor(2)
    }
}