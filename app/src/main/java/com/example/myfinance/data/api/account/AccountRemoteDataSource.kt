package com.example.myfinance.data.api.account


class AccountRemoteDataSource(
    private val accountApi: AccountApi
) {
    suspend fun getAllAccounts() = accountApi.getAllAccounts()
}