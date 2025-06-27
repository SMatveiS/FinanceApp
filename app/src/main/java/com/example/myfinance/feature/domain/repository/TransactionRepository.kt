package com.example.myfinance.feature.domain.repository

import com.example.myfinance.data.model.TransactionDto
import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.feature.utils.NetworkResult
import retrofit2.Response

interface TransactionRepository {

    suspend fun getTransaction(id: Int): Response<TransactionDto>

    suspend fun addTransaction(transaction: TransactionDto): Response<TransactionDto>

    suspend fun updateTransaction(id: Int, transaction: TransactionDto): Response<TransactionDto>

    suspend fun deleteTransaction(id: Int): Response<TransactionDto>

    suspend fun getTransactionForPeriod(
        id: Int,
        startDate: String,
        endDate: String
    ): NetworkResult<List<Transaction>>
}
