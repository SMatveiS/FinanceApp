package com.example.myfinance.data.api.account

import com.example.myfinance.data.model.AccountDto
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
    suspend fun getAllAccounts(): Response<List<AccountDto>>

    @PUT("accounts/{id}")
    suspend fun updateAccount(
        @Path("id") id: Int,
        @Body account: AccountDto
    ): Response<AccountDto>

}
