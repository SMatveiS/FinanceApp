package com.example.myfinance.domain.repository

import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.domain.model.Transaction

/**
 * Интерфейс репозитория транзакций для доменного слоя
 */

interface TransactionRepository {

    suspend fun getTransaction(id: Int): NetworkResult<Transaction>

    suspend fun addTransaction(transaction: Transaction): NetworkResult<Transaction>

    suspend fun updateTransaction(id: Int, transaction: Transaction): NetworkResult<Transaction>

    suspend fun deleteTransaction(id: Int): NetworkResult<Transaction>

    suspend fun getTransactionForPeriod(
        id: Int,
        startDate: String,
        endDate: String
    ): NetworkResult<List<Transaction>>

}
