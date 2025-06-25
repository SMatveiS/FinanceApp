package com.example.myfinance.data.api.transaction

import com.example.myfinance.data.model.TransactionDto
import com.example.myfinance.feature.domain.repository.TransactionRepository
import com.example.myfinance.feature.utils.BaseApiResponse
import com.example.myfinance.feature.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

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

    override suspend fun getTransactionForPeriod(id: Int, startDate: String, endDate: String) =
        transactionRemoteDataSource.getTransactionsForPeriod(
            id = id,
            startDate = startDate,
            endDate = endDate
        )
}