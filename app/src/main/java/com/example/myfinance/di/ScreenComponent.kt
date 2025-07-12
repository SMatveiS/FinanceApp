package com.example.myfinance.di

import androidx.lifecycle.ViewModel
import com.example.myfinance.di.module.viewmodel.ViewModelKey
import com.example.myfinance.ui.feature.presentation.account.viewmodel.AccountViewModel
import com.example.myfinance.ui.feature.presentation.categories.viewmodel.CategoryViewModel
import com.example.myfinance.ui.feature.presentation.expenses.viewmodel.ExpenseViewModel
import com.example.myfinance.ui.feature.presentation.incomes.viewmodel.IncomesViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@ScreenScope
@Subcomponent(modules = [ScreenModule::class])
interface ScreenComponent {

    val accountViewModel: AccountViewModel
    val categoryViewModel: CategoryViewModel
    val expenseViewModel: ExpenseViewModel
    val incomesViewModel: IncomesViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): ScreenComponent
    }
}

@Module
interface ScreenModule {

    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    fun bindAccountViewModel(accountViewModel: AccountViewModel): ViewModel

    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    fun bindCategoryViewModel(categoryViewModel: CategoryViewModel): ViewModel

    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(ExpenseViewModel::class)
    fun bindExpenseViewModel(expenseViewModel: ExpenseViewModel): ViewModel

    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(IncomesViewModel::class)
    fun bindIncomesViewModel(incomesViewModel: IncomesViewModel): ViewModel
}