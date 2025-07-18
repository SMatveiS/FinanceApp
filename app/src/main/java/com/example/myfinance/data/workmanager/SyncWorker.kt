package com.example.myfinance.data.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.example.myfinance.di.module.workmanager.ChildWorkerFactory
import com.example.myfinance.domain.usecase.transaction.SyncTransactionsUseCase
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SyncWorker @Inject constructor(
    appContext: Context,
    params: WorkerParameters,
    private val syncTransactionsUseCase: SyncTransactionsUseCase
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {

        val result = syncTransactionsUseCase()
        result.fold(
            onSuccess = { return Result.success() },

            onFailure = {
                return if (shouldRetry(result.exceptionOrNull())) {
                    Result.retry()
                } else {
                    Result.failure()
                }
            }
        )
    }

    private fun shouldRetry(exception: Throwable?): Boolean {
        return exception is IOException || exception is HttpException
    }

    class Factory @Inject constructor(
        private val syncTransactionsUseCase: SyncTransactionsUseCase
    ) : ChildWorkerFactory {

        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
            return SyncWorker(
                appContext,
                params,
                syncTransactionsUseCase
            )
        }
    }
}
