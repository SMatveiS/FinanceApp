package com.example.myfinance.data.api.transaction

import com.example.myfinance.data.model.TransactionDto
import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.domain.repository.TransactionRepository
import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.data.utils.map
import com.example.myfinance.data.utils.safeApiCall
import javax.inject.Inject

/**
 * Возвращает информацию о транзакциях внутри NetworkResult независимо от источника
 */

class TransactionRepositoryImpl @Inject constructor(
    private val transactionRemoteDataSource: TransactionRemoteDataSource
): TransactionRepository {

    override suspend fun getTransaction(id: Int) =
        transactionRemoteDataSource.getTransaction(id = id)

    override suspend fun addTransaction(transaction: TransactionDto) =
        transactionRemoteDataSource.addTransaction(transaction = transaction)

    override suspend fun updateTransaction(id: Int, transaction: TransactionDto) =
        transactionRemoteDataSource.updateTransaction(id = id, transaction = transaction)

    override suspend fun deleteTransaction(id: Int) =
        transactionRemoteDataSource.deleteTransaction(id = id)

    override suspend fun getTransactionForPeriod(
        id: Int,
        startDate: String,
        endDate: String
    ): NetworkResult<List<Transaction>> {

        val transactions = safeApiCall {
            transactionRemoteDataSource.getTransactionsForPeriod(
                id = id,
                startDate = startDate,
                endDate = endDate
            )
        }

        return transactions.map { transaction ->
            transaction.map { it.toDomain() }
        }
    }
}
