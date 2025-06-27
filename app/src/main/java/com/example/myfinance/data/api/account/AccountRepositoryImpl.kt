package com.example.myfinance.data.api.account

import com.example.myfinance.data.model.AccountDto
import com.example.myfinance.feature.domain.model.Account
import com.example.myfinance.feature.domain.repository.AccountRepository
import com.example.myfinance.feature.utils.BaseApiResponse
import com.example.myfinance.feature.utils.NetworkResult
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Возвращает информацию об аккаунтах внутри NetworkResult независимо от источника
 */

@Singleton
class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource
): AccountRepository, BaseApiResponse() {

    override suspend fun getAllAccounts(): NetworkResult<List<Account>> {
        val accounts = safeApiCall { accountRemoteDataSource.getAllAccounts() }
        return when (accounts) {
            is NetworkResult.Success ->
                NetworkResult.Success(accounts.data?.map { it.toDomain() })

            is NetworkResult.Error -> NetworkResult.Error(errorMessage = accounts.errorMessage)
        }
    }

    private fun AccountDto.toDomain() = Account(
        id = id,
        name = name,
        balance = balance.toDouble(),
        currency = currency
    )
}
