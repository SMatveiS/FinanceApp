package com.example.myfinance.ui.feature.presentation.account.viewmodel

import com.example.model.Account
import com.example.ui.ScreenState

/**
 * Состояние экрана счёта
 */

data class AccountState(
    val account: Account? = null,
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
