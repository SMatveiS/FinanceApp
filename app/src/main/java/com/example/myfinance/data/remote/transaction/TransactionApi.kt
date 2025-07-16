package com.example.myfinance.data.remote.transaction

import com.example.myfinance.data.model.TransactionRequestDto
import com.example.myfinance.data.model.TransactionResponseDto
import com.example.myfinance.data.model.TransactionShortResponseDto
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
    suspend fun addTransaction(@Body transaction: TransactionRequestDto): Response<TransactionShortResponseDto>

    @GET("transactions/{id}")
    suspend fun getTransaction(@Path("id") id: Int): Response<TransactionResponseDto>

    @PUT("transactions/{id}")
    suspend fun updateTransaction(
        @Path("id") id: Int,
        @Body transaction: TransactionRequestDto
    ): Response<TransactionResponseDto>

    @DELETE("transactions/{id}")
    suspend fun deleteTransaction(@Path("id") id: Int): Response<Void>

    @GET("transactions/account/{accountId}/period")
    suspend fun getTransactionsForPeriod(
        @Path("accountId") accountId: Int,
        @Query("startDate") startDate: String? = null,
        @Query("endDate") endDate: String? = null
    ): Response<List<TransactionResponseDto>>
}
