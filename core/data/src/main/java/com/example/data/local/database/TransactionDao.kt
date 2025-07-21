package com.example.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTransactions(transactions: List<TransactionEntity>)

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)

    @Query("DELETE FROM Transactions WHERE id = :transactionId")
    suspend fun deleteTransaction(transactionId: Int)

    @Transaction
    @Query("SELECT * FROM Transactions WHERE id = :transactionId")
    suspend fun getTransactionWithCategory(transactionId: Int): TransactionWithCategory?

    @Transaction
    @Query("SELECT * FROM Transactions WHERE date(transaction_date) BETWEEN :startDate AND :endDate")
    suspend fun getTransactionsWithCategoryForPeriod(startDate: String, endDate: String): List<TransactionWithCategory>
}