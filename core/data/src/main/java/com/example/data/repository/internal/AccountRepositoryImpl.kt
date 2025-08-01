package com.example.data.repository.internal

import com.example.data.local.datastore.AccountManager
import com.example.data.model.toDto
import com.example.data.remote.account.AccountRemoteDataSource
import com.example.data.utils.safeApiCall
import com.example.model.Account
import com.example.data.repository.external.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Возвращает информацию об аккаунтах внутри NetworkResult независимо от источника
 */

@Singleton
class AccountRepositoryImpl @Inject constructor(
    private val remoteDataSource: AccountRemoteDataSource,
    private val localDataSource: AccountManager
): AccountRepository {

    override suspend fun getAccount(): Result<Account> {
        return withContext(Dispatchers.IO) {

            val localAccount = localDataSource.getAccount()
            if (localAccount != null){
                return@withContext Result.success(localAccount)
            }

            // Если в DataStore нет информации о счёте, то делаем запрос на сервер
            val accountsResult = safeApiCall { remoteDataSource.getAllAccounts() }

            accountsResult.fold(
                onFailure = { Result.failure(it) },

                onSuccess = { accounts ->
                    val account = accounts.firstOrNull()?.toDomain()

                    if (account == null) {
                        Result.failure(Throwable("No accounts available"))
                    } else {
                        localDataSource.updateAccount(account)
                        Result.success(account)
                    }
                }
            )
        }
    }

    override suspend fun updateAccount(id: Int, account: Account): Result<Account> {
        return withContext(Dispatchers.IO) {

            val accountResult =
                safeApiCall { remoteDataSource.updateAccount(id, account.toDto()) }

            // Записываем данные в бд, только если удалось записать на сервер
            accountResult.map {
                val updatedAccount = it.toDomain()

                localDataSource.updateAccount(updatedAccount)
                updatedAccount
            }
        }
    }

    override suspend fun syncAccount(): Result<Account> {
        return withContext(Dispatchers.IO) {
            val accountsResult = safeApiCall { remoteDataSource.getAllAccounts() }

            accountsResult.fold(
                onFailure = { Result.failure(it) },

                onSuccess = { accounts ->
                    val account = accounts.firstOrNull()?.toDomain()

                    if (account == null) {
                        Result.failure(Throwable("No accounts available"))
                    } else {
                        localDataSource.updateAccount(account)
                        Result.success(account)
                    }
                }
            )
        }
    }
}