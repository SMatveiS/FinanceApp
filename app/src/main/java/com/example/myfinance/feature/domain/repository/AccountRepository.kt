package com.example.myfinance.feature.domain.repository

import com.example.myfinance.feature.domain.model.Account
import com.example.myfinance.feature.utils.NetworkResult

/**
 * Интерфейс репозитория аккаунтов для доменного слоя
 */

interface AccountRepository {

    suspend fun getAllAccounts(): NetworkResult<List<Account>>

}
