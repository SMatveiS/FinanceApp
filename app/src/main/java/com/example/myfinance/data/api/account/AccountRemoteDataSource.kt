package com.example.myfinance.data.api.account

import javax.inject.Inject


class AccountRemoteDataSource @Inject constructor(
    private val accountApi: AccountApi
) {
    suspend fun getAllAccounts() = accountApi.getAllAccounts()
}