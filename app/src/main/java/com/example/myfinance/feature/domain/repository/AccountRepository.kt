package com.example.myfinance.feature.domain.repository

import com.example.myfinance.data.model.AccountDto
import retrofit2.Response

interface AccountRepository {

    suspend fun getAllAccounts(): Response<List<AccountDto>>

}
