package com.example.data.repository.external

import com.example.model.Transaction
import com.example.model.TransactionBrief
import com.example.data.model.TransactionRequestDto

/**
 * Интерфейс репозитория транзакций для доменного слоя
 */

interface TransactionRepository {

    suspend fun getTransaction(id: Int): Result<Transaction>

    suspend fun addTransaction(transaction: Transaction): Result<TransactionBrief>

    suspend fun updateTransaction(id: Int, transaction: Transaction): Result<Transaction>

    suspend fun deleteTransaction(id: Int): Result<Void?>

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

    suspend fun addTransactionOnServer(transaction: TransactionRequestDto): Result<TransactionBrief>

    suspend fun updateTransactionOnServer(id: Int, transaction: TransactionRequestDto): Result<Transaction>

    suspend fun deleteTransactionOnServer(id: Int): Result<Void?>

    suspend fun deleteTransactionOnDb(id: Int)
}
