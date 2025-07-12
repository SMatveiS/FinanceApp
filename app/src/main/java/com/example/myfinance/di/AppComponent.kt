package com.example.myfinance.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.myfinance.di.module.ApiModule
import com.example.myfinance.di.module.AppModule
import com.example.myfinance.di.module.RepositoryModule
import com.example.myfinance.di.module.viewmodel.AssistedChangeTransactionFactory
import com.example.myfinance.di.module.viewmodel.AssistedTransactionsHistoryFactory
import com.example.myfinance.di.module.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ApiModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
])
interface AppComponent {

    fun viewModelProviderFactory(): ViewModelProvider.Factory
    fun assistedTransactionsHistoryFactory(): AssistedTransactionsHistoryFactory
    fun assistedChangeTransactionFactory(): AssistedChangeTransactionFactory

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }
}