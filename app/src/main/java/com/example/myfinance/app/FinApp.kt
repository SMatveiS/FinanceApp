package com.example.myfinance.app

import android.app.Application
import android.content.Context
import com.example.myfinance.di.AppComponent
import com.example.myfinance.di.DaggerAppComponent

/**
 * Точка входа для Hilt
 */

class FinApp: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is FinApp -> appComponent
        else -> applicationContext.appComponent
    }