package com.example.myfinance.data.api.account

import com.example.myfinance.feature.domain.repository.AccountRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource
): AccountRepository {

    override suspend fun getAllAccounts() = accountRemoteDataSource.getAllAccounts()

}