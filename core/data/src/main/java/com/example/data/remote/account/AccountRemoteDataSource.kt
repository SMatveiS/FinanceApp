package com.example.data.remote.account

import com.example.data.model.AccountRequestDto
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

    suspend fun updateAccount(id: Int, account: AccountRequestDto) = accountApi.updateAccount(id, account)
}
