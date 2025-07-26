package com.example.myfinance.di

import androidx.lifecycle.ViewModel
import com.example.myfinance.di.module.viewmodel.ViewModelKey
import com.example.myfinance.di.module.viewmodel.ViewModelModule
import com.example.myfinance.ui.feature.presentation.account.viewmodel.AccountViewModel
import com.example.myfinance.ui.feature.presentation.categories.viewmodel.CategoryViewModel
import com.example.myfinance.ui.feature.presentation.expenses.viewmodel.ExpenseViewModel
import com.example.myfinance.ui.feature.presentation.incomes.viewmodel.IncomesViewModel
import com.example.myfinance.ui.feature.presentation.pickMainColor.PickMainColorViewModel
import com.example.myfinance.ui.feature.presentation.settings.viewmodel.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@ScreenScope
@Subcomponent(modules = [
    ScreenModule::class,
    ViewModelModule::class
])
interface ScreenComponent {

    val accountViewModel: AccountViewModel
    val categoryViewModel: CategoryViewModel
    val expenseViewModel: ExpenseViewModel
    val incomesViewModel: IncomesViewModel
    val settingsViewModel: SettingsViewModel
    val pickMainColorViewModel: PickMainColorViewModel

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

    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(PickMainColorViewModel::class)
    fun bindPickMainColorViewModel(pickMainColorViewModel: PickMainColorViewModel): ViewModel
}