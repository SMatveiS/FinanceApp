package com.example.myfinance.app

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModelProvider
import com.example.myfinance.di.module.viewmodel.AssistedChangeTransactionFactory
import com.example.myfinance.di.module.viewmodel.AssistedTransactionsHistoryFactory

val LocalViewModelFactory = staticCompositionLocalOf<ViewModelProvider.Factory> { error("No viewModelFactory provided") }

val LocalAssistedTransactionsHistoryFactory = staticCompositionLocalOf<AssistedTransactionsHistoryFactory> {
    error("No assisted transactionsHistory factory provided")
}

val LocalAssistedChangeTransactionFactory = staticCompositionLocalOf<AssistedChangeTransactionFactory> {
    error("No assisted changeTransaction factory provided")
}