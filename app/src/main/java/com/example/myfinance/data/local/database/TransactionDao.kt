package com.example.myfinance.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TransactionDao {

    @Insert
    suspend fun addTransaction(transaction: TransactionEntity)

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)

    @Query("DELETE FROM Transactions WHERE id = :transactionId")
    suspend fun deleteTransaction(transactionId: Long)

    @Query("SELECT * FROM Transactions WHERE id = :transactionId")
    suspend fun getTransaction(transactionId: Long): TransactionEntity

    @Query("SELECT * FROM Transactions WHERE date(transaction_date) BETWEEN :startDate AND :endDate")
    suspend fun getTransactionsForPeriod(startDate: String, endDate: String): List<TransactionEntity>
}