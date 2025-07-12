package com.example.myfinance.data.api.account

import com.example.myfinance.data.model.AccountResponseDto
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

    suspend fun updateAccount(id: Int, account: AccountResponseDto) = accountApi.updateAccount(id, account)
}
