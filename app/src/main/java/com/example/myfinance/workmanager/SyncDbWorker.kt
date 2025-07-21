package com.example.myfinance.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.example.myfinance.di.module.workmanager.ChildWorkerFactory
import com.example.domain.usecase.SyncDbUseCase
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Создаёт workManager, который синхронизирует данные с сервера с локальными данными
 */

class SyncDbWorker @Inject constructor(
    appContext: Context,
    params: WorkerParameters,
    private val syncDbUseCase: SyncDbUseCase
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {

        val result = syncDbUseCase()
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
        private val syncDbUseCase: SyncDbUseCase
    ) : ChildWorkerFactory {

        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
            return SyncDbWorker(
                appContext,
                params,
                syncDbUseCase
            )
        }
    }
}
