package com.example.myfinance.data.api.account

import com.example.myfinance.data.model.AccountDto
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Удалённый источник данных, связанных с аккаунтами
 */

@Singleton
class AccountRemoteDataSource @Inject constructor(
    private val accountApi: AccountApi
) {
    suspend fun getAllAccounts() = accountApi.getAllAccounts()

    suspend fun updateAccount(id: Int, account: AccountDto) = accountApi.updateAccount(id, account)
}
