package com.example.myfinance.data.api

import com.example.myfinance.data.dto.AccountDto
import retrofit2.http.GET

interface AccountsApi {
    @GET("accounts")
    suspend fun getAccount(): List<AccountDto>
}