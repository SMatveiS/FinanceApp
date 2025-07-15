package com.example.myfinance.domain.repository

import com.example.myfinance.domain.model.Transaction

/**
 * Интерфейс репозитория транзакций для доменного слоя
 */

interface TransactionRepository {

    suspend fun getTransaction(id: Int): Result<Transaction>

    suspend fun addTransaction(transaction: Transaction)

    suspend fun updateTransaction(id: Int, transaction: Transaction): Result<Transaction>

    suspend fun deleteTransaction(id: Int): Result<Transaction>

    suspend fun getTransactionForPeriod(
        id: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>>

}
