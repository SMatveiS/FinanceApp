package com.example.myfinance.data.api.account

import com.example.myfinance.data.model.AccountDto
import retrofit2.Response
import retrofit2.http.GET

interface AccountApi {
    @GET("accounts")
    suspend fun getAllAccounts(): Response<List<AccountDto>>
}