package com.example.myfinance.di.module.workmanager

import com.example.myfinance.workmanager.SyncDbWorker
import com.example.myfinance.workmanager.SyncOperationsWorker
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