package com.example.myfinance.feature.presentation.account.viewmodel

import com.example.myfinance.feature.domain.model.Account
import com.example.myfinance.feature.presentation.ScreenState

/**
 * Состояние экрана счёта
 */

data class AccountState(
    val account: Account? = null,
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
