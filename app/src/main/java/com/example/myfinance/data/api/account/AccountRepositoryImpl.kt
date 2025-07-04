package com.example.myfinance.data.api.account

import com.example.myfinance.domain.model.Account
import com.example.myfinance.domain.repository.AccountRepository
import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.data.utils.map
import com.example.myfinance.data.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.map

/**
 * Возвращает информацию об аккаунтах внутри NetworkResult независимо от источника
 */

@Singleton
class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource
): AccountRepository {

    override suspend fun getAllAccounts(): NetworkResult<List<Account>> {
        return withContext(Dispatchers.IO) {

            val accounts = safeApiCall { accountRemoteDataSource.getAllAccounts() }

            accounts.map { accounts ->
                accounts.map { it.toDomain() }
            }
        }
    }

    override suspend fun updateAccount(id: Int, account: Account): NetworkResult<Account> {
        return withContext(Dispatchers.IO) {

            val accounts = safeApiCall { accountRemoteDataSource.updateAccount(id, account.toDto()) }

            accounts.map { account ->
                account.toDomain()
            }
        }
    }
}
