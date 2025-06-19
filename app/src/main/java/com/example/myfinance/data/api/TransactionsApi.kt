package com.example.myfinance.data.api

import com.example.myfinance.data.dto.TransactionDto
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface TransactionsApi {
    @POST("transactions")
    suspend fun addTransaction(transaction: TransactionDto): TransactionDto

    @GET("transactions/{id}")
    suspend fun getTransaction(@Path("id") id: Int): TransactionDto

    @PUT("transactions/{id}")
    suspend fun updateTransaction(@Path("id") id: Int, transaction: TransactionDto): TransactionDto

    @DELETE("transactions/{id}")
    suspend fun deleteTransaction(@Path("id") id: Int)

    @GET("transactions/account/{accountId}/period")
    suspend fun getTransactionsForPeriod(@Path("accountId") accountId: Int, startDate: String, endDate: String): List<TransactionDto>
}