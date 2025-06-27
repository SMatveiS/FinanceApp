package com.example.myfinance.feature.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfinance.feature.domain.usecase.GetAccountIdUseCase
import com.example.myfinance.feature.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class AccountIdState {
    object Loading : AccountIdState()
    data class Success(val id: Int?) : AccountIdState()
    data class Error(val message: String) : AccountIdState()
}

class AccountViewModel(
    private val getAccountIdUseCase: GetAccountIdUseCase
) : ViewModel() {

    private val _accountIdState = MutableStateFlow<AccountIdState>(AccountIdState.Loading)
    val accountIdState: StateFlow<AccountIdState> = _accountIdState.asStateFlow()

    init {
        loadAccountId()
    }

    private fun loadAccountId() {
        viewModelScope.launch {
            _accountIdState.value = AccountIdState.Loading
            val accountIdResult = getAccountIdUseCase()
            when (accountIdResult) {
                is NetworkResult.Success -> { _accountIdState.value = AccountIdState.Success(accountIdResult.data)}
                is NetworkResult.Error -> { _accountIdState.value = AccountIdState.Error(
                    accountIdResult.errorMessage ?: "Unknown error"
                )}
                is NetworkResult.Loading -> {}
            }
        }
    }
}