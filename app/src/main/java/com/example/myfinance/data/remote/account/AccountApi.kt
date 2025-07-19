package com.example.myfinance.data.remote.account

import com.example.myfinance.data.model.AccountRequestDto
import com.example.myfinance.data.model.AccountResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Интерфейс для связанных с аккаунтами запросов в сеть
 */

interface AccountApi {
    @GET("accounts")
    suspend fun getAllAccounts(): Response<List<AccountResponseDto>>

    @PUT("accounts/{id}")
    suspend fun updateAccount(
        @Path("id") id: Int,
        @Body account: AccountRequestDto
    ): Response<AccountResponseDto>

}
