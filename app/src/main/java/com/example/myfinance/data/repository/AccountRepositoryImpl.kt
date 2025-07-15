package com.example.myfinance.data.repository

import com.example.myfinance.data.local.datastore.AccountManager
import com.example.myfinance.data.remote.account.AccountRemoteDataSource
import com.example.myfinance.data.utils.safeApiCall
import com.example.myfinance.domain.model.Account
import com.example.myfinance.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Возвращает информацию об аккаунтах внутри NetworkResult независимо от источника
 */

@Singleton
class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource,
    private val accountManager: AccountManager
): AccountRepository {

    override suspend fun getAccount(): Result<Account> {
        return withContext(Dispatchers.IO) {

            val localAccount = accountManager.getAccount()

            if (localAccount != null){
                Result.success(localAccount)
            } else {

                val accountsResult = safeApiCall { accountRemoteDataSource.getAllAccounts() }

                accountsResult.fold(
                    onFailure = { Result.failure(it) },

                    onSuccess = { accounts ->
                        val account = accounts.firstOrNull()?.toDomain()

                        if (account == null) {
                            Result.failure(Throwable("No accounts available"))
                        } else {
                            accountManager.updateAccount(account)
                            Result.success(account)
                        }
                    }
                )
            }
        }
    }

    override suspend fun updateAccount(id: Int, account: Account): Result<Account> {
        return withContext(Dispatchers.IO) {

            val accountResult =
                safeApiCall { accountRemoteDataSource.updateAccount(id, account.toDto()) }

            accountResult.map {
                val updatedAccount = it.toDomain()

                accountManager.updateAccount(updatedAccount)
                updatedAccount
            }
        }
    }
}