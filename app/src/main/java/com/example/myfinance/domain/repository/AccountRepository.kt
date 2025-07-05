package com.example.myfinance.domain.repository

import com.example.myfinance.domain.model.Account
import com.example.myfinance.data.utils.NetworkResult

/**
 * Интерфейс репозитория аккаунтов для доменного слоя
 */

interface AccountRepository {

    suspend fun getAllAccounts(): NetworkResult<List<Account>>

    suspend fun updateAccount(id: Int, account: Account): NetworkResult<Account>
}
