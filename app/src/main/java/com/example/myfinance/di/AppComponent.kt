package com.example.myfinance.di

import android.content.Context
import com.example.myfinance.app.FinApp
import com.example.myfinance.di.module.ApiModule
//import com.example.myfinance.di.module.AppModule
import com.example.myfinance.di.module.DatabaseModule
import com.example.myfinance.di.module.RepositoryModule
import com.example.myfinance.di.module.viewmodel.ViewModelModule
import com.example.myfinance.di.module.workmanager.WorkerFactory
import com.example.myfinance.di.module.workmanager.WorkerModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(modules = [
//    AppModule::class,
    ApiModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    DatabaseModule::class,
    WorkerModule::class
])
interface AppComponent {
    fun activityComponentFactory(): ActivityComponent.Factory
    fun workerFactory(): WorkerFactory

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