package com.example.myfinance.data.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.example.myfinance.data.local.database.pending_operation.PendingOperation
import com.example.myfinance.data.local.database.pending_operation.PendingOperationsDataSource
import com.example.myfinance.di.module.workmanager.ChildWorkerFactory
import com.example.myfinance.domain.repository.TransactionRepository
import javax.inject.Inject

/**
 * Создаёт workManager, который производит все операции добавления, обновления, удаления, которые произошли, пока был выключен интернет
 */

class SyncOperationsWorker @Inject constructor(
    appContext: Context,
    params: WorkerParameters,
    private val transactionRepository: TransactionRepository,
    private val pendingOperationsDataSource: PendingOperationsDataSource
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        while (true) {
            val operation = pendingOperationsDataSource.getNextOperation() ?: break

            val result = when (operation) {
                is PendingOperation.Add -> {
                    val result = transactionRepository.addTransactionOnServer(operation.transaction)
                    transactionRepository.deleteTransactionOnDb(operation.id)

                    result
                }
                is PendingOperation.Update ->
                    transactionRepository.updateTransactionOnServer(operation.id, operation.transaction)
                is PendingOperation.Delete ->
                    transactionRepository.deleteTransactionOnServer(operation.id)
            }

            result.fold(
                onSuccess = { pendingOperationsDataSource.removeOperation() },

                onFailure = { Result.retry() }
            )
        }
        return Result.success()
    }


    class Factory @Inject constructor(
        private val transactionRepository: TransactionRepository,
        private val pendingOperationsDataSource: PendingOperationsDataSource
    ) : ChildWorkerFactory {
        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
            return SyncOperationsWorker(
                appContext,
                params,
                transactionRepository,
                pendingOperationsDataSource
            )
        }
    }
}