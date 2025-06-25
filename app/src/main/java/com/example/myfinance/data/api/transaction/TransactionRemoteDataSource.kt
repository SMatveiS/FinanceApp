package com.example.myfinance.data.api.transaction

import com.example.myfinance.data.model.TransactionDto

class TransactionRemoteDataSource(
    private val transactionApi: TransactionApi
) {
    suspend fun getTransaction(id: Int) =
        transactionApi.getTransaction(id = id)

    suspend fun addTransaction(transaction: TransactionDto) =
        transactionApi.addTransaction(transaction = transaction)

    suspend fun updateTransaction(id: Int, transaction: TransactionDto) =
        transactionApi.updateTransaction(id = id, transaction = transaction)

    suspend fun deleteTransaction(id: Int) =
        transactionApi.deleteTransaction(id = id)

    suspend fun getTransactionsForPeriod(id: Int, startDate: String, endDate: String) =
        transactionApi.getTransactionsForPeriod(accountId = id, startDate = startDate, endDate = endDate)
}