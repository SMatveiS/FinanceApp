package com.example.myfinance.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfinance.ui.feature.presentation.change_transaction.viewmodel.ChangeTransactionViewModel
import com.example.myfinance.ui.feature.presentation.transactions_history.viewmodel.TransactionsHistoryViewModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider = viewModels[modelClass]
            ?: throw IllegalArgumentException("Unknown model class $modelClass")

        return viewModelProvider.get() as T
    }
}

@Singleton
class AssistedTransactionsHistoryFactory @Inject constructor(
    private val factory: TransactionsHistoryViewModel.Factory
) {

    fun create(isIncome: Boolean): TransactionsHistoryViewModel {
        return factory.create(isIncome)
    }
}

@Singleton
class AssistedChangeTransactionFactory @Inject constructor(
    private val factory: ChangeTransactionViewModel.Factory
) {

    fun create(isIncome: Boolean, transactionId: Int?): ChangeTransactionViewModel {
        return factory.create(isIncome, transactionId)
    }
}