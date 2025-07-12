package com.example.myfinance.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfinance.ui.feature.presentation.account.viewmodel.AccountViewModel
import com.example.myfinance.ui.feature.presentation.categories.viewmodel.CategoryViewModel
import com.example.myfinance.ui.feature.presentation.expenses.viewmodel.ExpenseViewModel
import com.example.myfinance.ui.feature.presentation.incomes.viewmodel.IncomesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    fun bindAccountViewModel(accountViewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    fun bindCategoryViewModel(categoryViewModel: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExpenseViewModel::class)
    fun bindExpenseViewModel(expenseViewModel: ExpenseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomesViewModel::class)
    fun bindIncomesViewModel(incomesViewModel: IncomesViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(ChangeTransactionViewModel::class)
//    fun bindChangeTransactionViewModel(changeTransactionViewModel: ChangeTransactionViewModel): ViewModel

}

