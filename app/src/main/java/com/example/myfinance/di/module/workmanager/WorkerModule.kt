package com.example.myfinance.di.module.workmanager

import com.example.myfinance.data.workmanager.SyncWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(SyncWorker::class)
    abstract fun bindSyncWorkerFactory(factory: SyncWorker.Factory): ChildWorkerFactory
}