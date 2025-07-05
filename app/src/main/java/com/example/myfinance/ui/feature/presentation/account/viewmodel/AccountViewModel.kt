package com.example.myfinance.ui.feature.presentation.account.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.domain.usecase.GetAccountUseCase
import com.example.myfinance.ui.feature.presentation.ScreenState
import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.domain.usecase.UpdateAccountUseCase
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
    private val getAccountUseCase: GetAccountUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase
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
                            account = accountResult.data,
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

    fun updateTempName(name: String) {
        _state.update { it.copy(
            account = it.account?.copy(name = name)
        ) }
    }

    fun updateTempBalance(balance: Double) {
        _state.update { it.copy(
            account = it.account?.copy(balance = balance)
        ) }
    }

    fun updateTempCurrency(currency: String) {
        _state.update { it.copy(
            account = it.account?.copy(currency = currency)
        ) }
    }

    fun updateAccount() {
        viewModelScope.launch {
            val account = state.value.account ?: return@launch

            try {
                updateAccountUseCase(account)
            } catch (e: Exception) {
                _state.update { it.copy(
                    errorMessage = "Ошибка сохранения: ${e.localizedMessage}",
                    screenState = ScreenState.ERROR
                ) }
            }
        }
    }
}
