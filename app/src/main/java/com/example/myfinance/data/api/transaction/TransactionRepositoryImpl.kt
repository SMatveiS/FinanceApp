package com.example.myfinance.data.api.transaction

import com.example.myfinance.data.model.TransactionDto
import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.domain.repository.TransactionRepository
import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.data.utils.map
import com.example.myfinance.data.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Возвращает информацию о транзакциях внутри NetworkResult независимо от источника
 */

class TransactionRepositoryImpl @Inject constructor(
    private val transactionRemoteDataSource: TransactionRemoteDataSource
): TransactionRepository {

    override suspend fun getTransaction(id: Int) = withContext(Dispatchers.IO) {
        transactionRemoteDataSource.getTransaction(id = id)
    }

    override suspend fun addTransaction(transaction: TransactionDto) = withContext(Dispatchers.IO) {
        transactionRemoteDataSource.addTransaction(transaction = transaction)
    }

    override suspend fun updateTransaction(
        id: Int,
        transaction: TransactionDto
    ) = withContext(Dispatchers.IO) {

        transactionRemoteDataSource.updateTransaction(id = id, transaction = transaction)
    }

    override suspend fun deleteTransaction(id: Int) = withContext(Dispatchers.IO) {
        transactionRemoteDataSource.deleteTransaction(id = id)
    }

    override suspend fun getTransactionForPeriod(
        id: Int,
        startDate: String,
        endDate: String
    ): NetworkResult<List<Transaction>> {

        return withContext(Dispatchers.IO) {

            val transactions = safeApiCall {
                transactionRemoteDataSource.getTransactionsForPeriod(
                    id = id,
                    startDate = startDate,
                    endDate = endDate
                )
            }

            transactions.map { transaction ->
                transaction.map { it.toDomain() }
            }
        }
    }
}
