package com.example.myfinance.data.api.account

import com.example.myfinance.domain.model.Account
import com.example.myfinance.domain.repository.AccountRepository
import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.data.utils.map
import com.example.myfinance.data.utils.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Возвращает информацию об аккаунтах внутри NetworkResult независимо от источника
 */

@Singleton
class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource
): AccountRepository {

    override suspend fun getAllAccounts(): NetworkResult<List<Account>> {
        val accounts = safeApiCall { accountRemoteDataSource.getAllAccounts() }

        return accounts.map { account ->
            account.map { it.toDomain() }
        }
    }
}
