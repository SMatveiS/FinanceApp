package com.example.myfinance.ui.feature.presentation.account.viewmodel

import com.example.model.Account
import com.example.model.DailyAmount
import com.example.ui.screenstate.ScreenState

/**
 * Состояние экрана счёта
 */

data class AccountState(
    val account: Account? = null,
    val dailyAmounts: List<DailyAmount> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)
