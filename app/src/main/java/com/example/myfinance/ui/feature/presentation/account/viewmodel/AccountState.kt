package com.example.myfinance.ui.feature.presentation.account.viewmodel

import com.example.myfinance.domain.model.Account
import com.example.myfinance.ui.feature.presentation.ScreenState

/**
 * Состояние экрана счёта
 */

data class AccountState(
    val account: Account? = null,
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
