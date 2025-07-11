package com.example.myfinance.app

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModelProvider
import com.example.myfinance.di.module.viewmodel.AssistedTransactionsHistoryFactory

val LocalViewModelFactory = staticCompositionLocalOf<ViewModelProvider.Factory> { error("No viewModelFactory provided") }

val LocalAssistedFactory = staticCompositionLocalOf<AssistedTransactionsHistoryFactory> {
    error("No assisted factory provided")
}