package com.example.myfinance.ui.feature.presentation.change_transaction.viewmodel

import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.ui.feature.presentation.ScreenState

data class ChangeTransactionState(
    val transaction: Transaction? = null,
    val screenState: ScreenState = ScreenState.LOADING,
    val errorMessage: String? = null
)