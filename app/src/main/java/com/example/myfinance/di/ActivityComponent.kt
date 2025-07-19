package com.example.myfinance.di

import androidx.activity.ComponentActivity
import com.example.myfinance.app.MainActivity
import com.example.myfinance.di.module.viewmodel.AssistedAnalysisFactory
import com.example.myfinance.di.module.viewmodel.AssistedChangeTransactionFactory
import com.example.myfinance.di.module.viewmodel.AssistedTransactionsHistoryFactory
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ActivityComponent {

    fun screenComponentFactory(): ScreenComponent.Factory

    fun assistedTransactionsHistoryFactory(): AssistedTransactionsHistoryFactory
    fun assistedChangeTransactionFactory(): AssistedChangeTransactionFactory
    fun assistedAnalysisFactory(): AssistedAnalysisFactory

    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: ComponentActivity): ActivityComponent
    }
}