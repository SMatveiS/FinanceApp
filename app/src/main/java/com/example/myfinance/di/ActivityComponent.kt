package com.example.myfinance.di

import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.myfinance.app.MainActivity
import com.example.myfinance.di.module.viewmodel.AssistedChangeTransactionFactory
import com.example.myfinance.di.module.viewmodel.AssistedTransactionsHistoryFactory
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun screenComponentFactory(): ScreenComponent.Factory

    fun assistedTransactionsHistoryFactory(): AssistedTransactionsHistoryFactory
    fun assistedChangeTransactionFactory(): AssistedChangeTransactionFactory

    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: ComponentActivity): ActivityComponent
    }
}

@Module
interface ActivityModule {
    @ActivityScope
    @Binds
    fun bindComponentActivity(activity: ComponentActivity): ComponentActivity
}