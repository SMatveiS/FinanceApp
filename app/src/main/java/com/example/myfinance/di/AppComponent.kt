package com.example.myfinance.di

import android.content.Context
import com.example.myfinance.app.FinApp
import com.example.myfinance.app.NetworkMonitor
import com.example.data.di.ApiModule
import com.example.data.di.DatabaseModule
import com.example.data.di.RepositoryModule
import com.example.myfinance.di.module.workmanager.WorkerFactory
import com.example.myfinance.di.module.workmanager.WorkerModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiModule::class,
    RepositoryModule::class,
    DatabaseModule::class,
    WorkerModule::class
])
interface AppComponent {
    fun activityComponentFactory(): ActivityComponent.Factory
    fun workerFactory(): WorkerFactory
    fun networkMonitor(): NetworkMonitor

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(app: FinApp)
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ScreenScope