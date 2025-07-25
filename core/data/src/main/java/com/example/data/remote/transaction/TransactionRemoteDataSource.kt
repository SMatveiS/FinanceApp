package com.example.data.remote.transaction

import com.example.data.model.TransactionRequestDto
import javax.inject.Inject

/**
 * Удалённый источник данных, связанных с транзакциями
 */

class TransactionRemoteDataSource @Inject constructor(
    private val transactionApi: TransactionApi
) {
    suspend fun getTransaction(id: Int) =
        transactionApi.getTransaction(id = id)

    suspend fun addTransaction(transaction: TransactionRequestDto) =
        transactionApi.addTransaction(transaction = transaction)

    suspend fun updateTransaction(id: Int, transaction: TransactionRequestDto) =
        transactionApi.updateTransaction(id = id, transaction = transaction)

    suspend fun deleteTransaction(id: Int) =
        transactionApi.deleteTransaction(id = id)

    suspend fun getTransactionsForPeriod(id: Int, startDate: String, endDate: String) =
        transactionApi.getTransactionsForPeriod(accountId = id, startDate = startDate, endDate = endDate)
}
