package com.example.myfinance.domain.repository

import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.domain.model.TransactionBrief

/**
 * Интерфейс репозитория транзакций для доменного слоя
 */

interface TransactionRepository {

    suspend fun getTransaction(id: Int): Result<Transaction>

    suspend fun addTransaction(transaction: Transaction): Result<TransactionBrief>

    suspend fun updateTransaction(id: Int, transaction: Transaction): Result<Transaction>

    suspend fun deleteTransaction(id: Int): Result<Void>

    suspend fun getTransactionForPeriod(
        id: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>>

    suspend fun syncTransactions(
        id: Int,
        startDate: String,
        endDate: String
    ): Result<Unit>
}
