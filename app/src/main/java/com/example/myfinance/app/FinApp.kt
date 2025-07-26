package com.example.myfinance.app

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.domain.usecase.settings.SyncFreqUseCase
import com.example.myfinance.di.AppComponent
import com.example.myfinance.di.DaggerAppComponent
import com.example.myfinance.di.module.workmanager.WorkerFactory
import com.example.myfinance.workmanager.SyncDbWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FinApp(): Application() {

    lateinit var appComponent: AppComponent
    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var currentSyncFreq: Int = 5

    @Inject
    lateinit var workerFactory: WorkerFactory

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    @Inject
    lateinit var syncFreqUseCase: SyncFreqUseCase

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)

        val config = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
        WorkManager.initialize(this, config)

        networkMonitor.startMonitoring()
        startSyncFrequencyObserver()
    }

    private fun startSyncFrequencyObserver() {
        appScope.launch {
            syncFreqUseCase.syncFreqFlow.collect { newFreq ->
                if (newFreq != currentSyncFreq) {
                    currentSyncFreq = newFreq
                    schedulePeriodicSync(newFreq.toLong())
                }
            }
        }
    }


    private fun schedulePeriodicSync(freq: Long) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncWorkRequest = PeriodicWorkRequest.Builder(
            SyncDbWorker::class.java,
            freq, TimeUnit.HOURS,
            15, TimeUnit.MINUTES
        ).setConstraints(constraints).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "periodic_sync",
            ExistingPeriodicWorkPolicy.KEEP,
            syncWorkRequest
        )
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is FinApp -> appComponent
        else -> applicationContext.appComponent
    }