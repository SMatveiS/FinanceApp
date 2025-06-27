package com.example.myfinance.data.api.transaction

import com.example.myfinance.data.model.TransactionDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Интерфейс для связанных с транзакциями запросов в сеть
 */

interface TransactionApi {
    @POST("transactions")
    suspend fun addTransaction(@Body transaction: TransactionDto): Response<TransactionDto>

    @GET("transactions/{id}")
    suspend fun getTransaction(@Path("id") id: Int): Response<TransactionDto>

    @PUT("transactions/{id}")
    suspend fun updateTransaction(
        @Path("id") id: Int,
        @Body transaction: TransactionDto
    ): Response<TransactionDto>

    @DELETE("transactions/{id}")
    suspend fun deleteTransaction(@Path("id") id: Int): Response<TransactionDto>

    @GET("transactions/account/{accountId}/period")
    suspend fun getTransactionsForPeriod(
        @Path("accountId") accountId: Int,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): Response<List<TransactionDto>>
}
