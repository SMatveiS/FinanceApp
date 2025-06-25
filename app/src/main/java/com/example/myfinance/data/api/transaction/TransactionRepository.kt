package com.example.myfinance.data.api.transaction

import com.example.myfinance.data.model.TransactionDto
import com.example.myfinance.feature.utils.BaseApiResponse
import com.example.myfinance.feature.utils.NetworkResult

class TransactionRepository(
    private val transactionRemoteDataSource: TransactionRemoteDataSource
): BaseApiResponse() {

    suspend fun getTransaction(id: Int): NetworkResult<TransactionDto> {
        return safeApiCall { transactionRemoteDataSource.getTransaction(id = id) }
    }

    suspend fun addTransaction(transaction: TransactionDto): NetworkResult<TransactionDto> {
        return safeApiCall { transactionRemoteDataSource.addTransaction(transaction = transaction) }
    }

    suspend fun updateTransaction(id: Int, transaction: TransactionDto): NetworkResult<TransactionDto> {
        return safeApiCall { transactionRemoteDataSource.updateTransaction(id = id, transaction = transaction) }
    }

    suspend fun deleteTransaction(id: Int): NetworkResult<TransactionDto> {
        return safeApiCall { transactionRemoteDataSource.deleteTransaction(id = id) }
    }

    suspend fun getTransactionForPeriod(id: Int, startDate: String, endDate: String): NetworkResult<List<TransactionDto>> {
        return safeApiCall {
            transactionRemoteDataSource.getTransactionsForPeriod(
                id = id,
                startDate = startDate,
                endDate = endDate
            )
        }
    }
}