package com.example.myfinance.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun provideApplicationContext(context: Context): Context {
        return context.applicationContext
    }
}