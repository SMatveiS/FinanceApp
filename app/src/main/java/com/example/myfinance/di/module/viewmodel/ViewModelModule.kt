package com.example.myfinance.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfinance.ui.feature.presentation.account.viewmodel.AccountViewModel
import com.example.myfinance.ui.feature.presentation.category.viewmodel.CategoryViewModel
import com.example.myfinance.ui.feature.presentation.change_transaction.viewmodel.ChangeTransactionViewModel
import com.example.myfinance.ui.feature.presentation.expenses.viewmodel.ExpenseViewModel
import com.example.myfinance.ui.feature.presentation.incomes.viewmodel.IncomesViewModel
import com.example.myfinance.ui.feature.presentation.transactions_history.viewmodel.TransactionsHistoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(accountViewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindCategoryViewModel(categoryViewModel: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExpenseViewModel::class)
    abstract fun bindExpenseViewModel(expenseViewModel: ExpenseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomesViewModel::class)
    abstract fun bindIncomesViewModel(incomesViewModel: IncomesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChangeTransactionViewModel::class)
    abstract fun bindChangeTransactionViewModel(changeTransactionViewModel: ChangeTransactionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionsHistoryViewModel::class)
    abstract fun bindTransactionHistoryViewModel(transactionsHistoryViewModel: TransactionsHistoryViewModel): ViewModel

}