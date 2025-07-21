package com.example.data.repository.internal

import android.content.Context
import com.example.data.local.database.TransactionDao
import com.example.data.local.database.pending_operation.PendingOperation
import com.example.data.local.database.pending_operation.PendingOperationsDataSource
import com.example.data.model.TransactionRequestDto
import com.example.data.model.toDto
import com.example.data.remote.transaction.TransactionRemoteDataSource
import com.example.data.utils.isInternetAvailable
import com.example.data.utils.safeApiCall
import com.example.model.Transaction
import com.example.model.TransactionBrief
import com.example.data.repository.external.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Возвращает информацию о транзакциях внутри NetworkResult независимо от источника
 */

class TransactionRepositoryImpl @Inject constructor(
    private val remoteDataSource: TransactionRemoteDataSource,
    private val localDataSource: TransactionDao,
    private val pendingOperationsDataSource: PendingOperationsDataSource,
    private val context: Context
): TransactionRepository {

    // Если добавлять транзакцию без интернета, то она сохранится в бд с этим id
    // (при синхронизации эта транзакция удалится и создастя новая с id с сервера)
    private var transactionInDbId: Int = -1

    override suspend fun getTransaction(id: Int): Result<Transaction> {
        return withContext(Dispatchers.IO) {

            localDataSource.getTransactionWithCategory(id)?.let {
                return@withContext Result.success(it.toDomain())
            }

            // Если транзакции нет в бд, то делаем запрос на сервер
            if (isInternetAvailable(context)) {
                val transactionResult = safeApiCall { remoteDataSource.getTransaction(id = id) }
                transactionResult.map { transaction ->
                    localDataSource.addTransactions( listOf(transaction.toEntity()) )
                    transaction.toDomain()
                }
            } else {
                Result.failure(Throwable("No internet"))
            }
        }

    }

    override suspend fun addTransaction(newTransaction: Transaction): Result<TransactionBrief> {
        return withContext(Dispatchers.IO) {

            if (isInternetAvailable(context)) {
                val transactionResult = safeApiCall {
                    remoteDataSource.addTransaction(transaction = newTransaction.toDto())
                }

                // Записываем данные в бд, только если удалось записать на сервер
                transactionResult.map { transaction ->
                    localDataSource.addTransactions(listOf(transaction.toEntity()))
                    transaction.toDomain()
                }
            } else {
                val transaction = newTransaction.toDto()

                // Если нет интернета, то добавляем транзакцию в бд и добавляем эту операцию в очередь операций при появлении интернета
                localDataSource.addTransactions(listOf(transaction.toEntity(transactionInDbId)))
                pendingOperationsDataSource.addOperation(PendingOperation.Add(transactionInDbId, transaction))

                --transactionInDbId

                Result.success(transaction.toDomain(transactionInDbId + 1))
            }
        }
    }

    override suspend fun updateTransaction(
        id: Int,
        updatedTransaction: Transaction
    ): Result<Transaction> {

        return withContext(Dispatchers.IO) {

            if (isInternetAvailable(context)) {
                val transactionResult = safeApiCall {
                    remoteDataSource.updateTransaction(
                        id = id,
                        transaction = updatedTransaction.toDto()
                    )
                }

                // Обновляем данные в бд, только если удалось обновить на сервере
                transactionResult.map { transaction ->
                    localDataSource.updateTransaction(transaction.toEntity())
                    transaction.toDomain()
                }
            } else {
                // Если нет интернета, то обновляем транзакцию в бд и добавляем эту операцию в очередь операций при появлении интернета
                val transaction = updatedTransaction.toDto()

                localDataSource.updateTransaction(transaction.toEntity(id))
                pendingOperationsDataSource.addOperation(PendingOperation.Update(id, transaction))

                Result.success(updatedTransaction)
            }
        }
    }

    override suspend fun deleteTransaction(id: Int): Result<Void?> {
        return withContext(Dispatchers.IO) {

            if (isInternetAvailable(context)) {
                val deleteResult = safeApiCall {
                    remoteDataSource.deleteTransaction(id = id)
                }

                // Удаляем данные в бд, только если удалось удалить на сервере
                deleteResult.map {
                    localDataSource.deleteTransaction(id)
                    it
                }
            } else {
                // Если нет интернета, то удаляем транзакцию в бд и добавляем эту операцию в очередь операций при появлении интернета
                localDataSource.deleteTransaction(id)
                pendingOperationsDataSource.addOperation(PendingOperation.Delete(id))

                Result.success(null)
            }
        }
    }

    override suspend fun getTransactionForPeriod(
        id: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>> {

        return withContext(Dispatchers.IO) {

            val localTransactions = localDataSource
                .getTransactionsWithCategoryForPeriod(startDate, endDate)
            if (!isInternetAvailable(context) || localTransactions.isNotEmpty()) {
                return@withContext Result.success(localTransactions.map { it.toDomain() })
            }

            // Если есть интернет и транзакций нет в бд, то делаем запрос на сервер
            val transactionsResult = safeApiCall {
                remoteDataSource.getTransactionsForPeriod(
                    id = id,
                    startDate = startDate,
                    endDate = endDate
                )
            }
            transactionsResult.map { transactions ->
                localDataSource.addTransactions( transactions.map { it.toEntity() })
                transactions.map { it.toDomain() }
            }
        }
    }

    override suspend fun syncTransactions(
        id: Int,
        startDate: String,
        endDate: String
    ): Result<Unit> {

        return withContext(Dispatchers.IO) {

            val transactionsResult = safeApiCall {
                remoteDataSource.getTransactionsForPeriod(
                    id = id,
                    startDate = startDate,
                    endDate = endDate
                )
            }
            // Возвращаем Result.success() или Result.failure
            transactionsResult.map { transactions ->
                localDataSource.addTransactions( transactions.map { it.toEntity() })
                return@map
            }
        }
    }

    override suspend fun addTransactionOnServer(
        transaction: TransactionRequestDto
    ): Result<TransactionBrief> {

        return withContext(Dispatchers.IO) {

            val transactionResult = safeApiCall {
                remoteDataSource.addTransaction(transaction = transaction)
            }

            transactionResult.map { it.toDomain() }
        }
    }

    override suspend fun updateTransactionOnServer(
        id: Int,
        transaction: TransactionRequestDto
    ): Result<Transaction> {

        return withContext(Dispatchers.IO) {

            val transactionResult = safeApiCall {
                remoteDataSource.updateTransaction(
                    id = id,
                    transaction = transaction
                )
            }

            transactionResult.map { it.toDomain() }
        }
    }

    override suspend fun deleteTransactionOnServer(id: Int): Result<Void?> {

        return withContext(Dispatchers.IO) {

            safeApiCall { remoteDataSource.deleteTransaction(id = id) }
        }
    }

    override suspend fun deleteTransactionOnDb(id: Int) {
        withContext(Dispatchers.IO) {
            localDataSource.deleteTransaction(id)
        }
    }
}
