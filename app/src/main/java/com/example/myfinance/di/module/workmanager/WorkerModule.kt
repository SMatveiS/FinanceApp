package com.example.myfinance.di.module.workmanager

import com.example.myfinance.data.workmanager.SyncOperationsWorker
import com.example.myfinance.data.workmanager.SyncDbWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(SyncDbWorker::class)
    abstract fun bindSyncDbWorkerFactory(factory: SyncDbWorker.Factory): ChildWorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(SyncOperationsWorker::class)
    abstract fun bindSyncOperationsWorkerFactory(factory: SyncOperationsWorker.Factory): ChildWorkerFactory
}