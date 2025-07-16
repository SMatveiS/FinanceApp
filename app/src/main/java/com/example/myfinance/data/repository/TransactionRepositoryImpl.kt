package com.example.myfinance.data.repository

import com.example.myfinance.data.local.database.TransactionDao
import com.example.myfinance.data.remote.transaction.TransactionRemoteDataSource
import com.example.myfinance.data.utils.safeApiCall
import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.domain.model.TransactionBrief
import com.example.myfinance.domain.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Возвращает информацию о транзакциях внутри NetworkResult независимо от источника
 */

class TransactionRepositoryImpl @Inject constructor(
    private val remoteDataSource: TransactionRemoteDataSource,
    private val localDataSource: TransactionDao
): TransactionRepository {

    override suspend fun getTransaction(id: Int): Result<Transaction> {
        return withContext(Dispatchers.IO) {

            localDataSource.getTransactionWithCategory(id)?.let {
                return@withContext Result.success(it.toDomain())
            }

            // Если транзакции нет в бд, то делаем запрос на сервер
            val transactionResult = safeApiCall { remoteDataSource.getTransaction(id = id) }
            transactionResult.map { transaction ->
                localDataSource.addTransactions( listOf(transaction.toEntity()) )
                transaction.toDomain()
            }
        }

    }

    override suspend fun addTransaction(transaction: Transaction): Result<TransactionBrief> {
        return withContext(Dispatchers.IO) {

            val transactionResult = safeApiCall {
                remoteDataSource.addTransaction(transaction = transaction.toDto())
            }

            // Записываем данные в бд, только если удалось записать на сервер
            transactionResult.map { transaction ->
                localDataSource.addTransactions( listOf(transaction.toEntity()) )
                transaction.toDomain()
            }
        }
    }

    override suspend fun updateTransaction(
        id: Int,
        newTransaction: Transaction
    ): Result<Transaction> {

        return withContext(Dispatchers.IO) {

            val transactionResult = safeApiCall {
                remoteDataSource.updateTransaction(
                    id = id,
                    transaction = newTransaction.toDto()
                )
            }

            // Обновляем данные в бд, только если удалось обновить на сервере
            transactionResult.map { transaction->
                localDataSource.updateTransaction(transaction.toEntity())
                transaction.toDomain()
            }
        }
    }

    override suspend fun deleteTransaction(id: Int): Result<Void> {
        return withContext(Dispatchers.IO) {

            val deleteResult = safeApiCall {
                remoteDataSource.deleteTransaction(id = id)
            }

            // Удаляем данные в бд, только если удалось удалить на сервере
            deleteResult.map {
                localDataSource.deleteTransaction(id)
                it
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
            if (localTransactions.isNotEmpty()) {
                return@withContext Result.success(localTransactions.map { it.toDomain() })
            }

            // Если транзакций нет в бд, то делаем запрос на сервер
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
}