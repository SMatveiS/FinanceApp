package com.example.myfinance.ui.feature.presentation.sync_choice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.settings.SyncFreqUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SyncChoiceViewModel @Inject constructor(
    private val syncFreqUseCase: SyncFreqUseCase
): ViewModel() {

    private val _syncFreq = MutableStateFlow(1)
    val syncFreq = _syncFreq.asStateFlow()

    init {
        viewModelScope.launch {
            _syncFreq.update { syncFreqUseCase.syncFreqFlow.first() }
        }
    }

    fun setSyncFrequency(freq: Int) {
        viewModelScope.launch {
            syncFreqUseCase.setSyncFreq(freq)
        }
    }
}