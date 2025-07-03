package com.example.myfinance.ui.feature.presentation.account.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.domain.usecase.GetAccountUseCase
import com.example.myfinance.ui.feature.presentation.ScreenState
import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.ui.feature.presentation.account.screen.getCurrencySymbol
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Хранит состояние экрана счёта
 */

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AccountState>(AccountState())
    val state = _state.asStateFlow()

    init {
        getAccount()
    }

    fun getAccount() {
        viewModelScope.launch {
            _state.update { it.copy(
                screenState = ScreenState.LOADING,
                errorMessage = null
            ) }

            try {
                val accountResult = getAccountUseCase()
                when (accountResult) {
                    is NetworkResult.Success -> {
                        _state.update { it.copy(
                            account = accountResult.data.copy(
                                currency = getCurrencySymbol(accountResult.data.currency)
                            ),
                            screenState = ScreenState.SUCCESS
                        ) }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                errorMessage = accountResult.errorMessage,
                                screenState = ScreenState.ERROR
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _state.update { it.copy(
                    errorMessage = "Ошибка: ${e.localizedMessage ?: "Неизвестная ошибка"}",
                    screenState = ScreenState.ERROR
                ) }
            }
        }
    }
}
