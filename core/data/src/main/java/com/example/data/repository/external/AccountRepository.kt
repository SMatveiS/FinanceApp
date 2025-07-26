package com.example.data.repository.external

import com.example.model.Account

/**
 * Интерфейс репозитория аккаунтов для доменного слоя
 */

interface AccountRepository {

    suspend fun getAccount(): Result<Account>

    suspend fun updateAccount(id: Int, account: Account): Result<Account>

    suspend fun syncAccount(): Result<Account>
}
