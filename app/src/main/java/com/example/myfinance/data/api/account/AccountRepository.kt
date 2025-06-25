package com.example.myfinance.data.api.account

import com.example.myfinance.data.model.AccountDto
import com.example.myfinance.feature.utils.BaseApiResponse
import com.example.myfinance.feature.utils.NetworkResult

class AccountRepository(
    private val accountRemoteDataSource: AccountRemoteDataSource
): BaseApiResponse() {

    //    suspend fun getAllAccounts(): NetworkResult<List<AccountDto>> {
//        return safeApiCall { accountRemoteDataSource.getAllAccounts() }
//    }
    suspend fun getAllAccounts() = accountRemoteDataSource.getAllAccounts()

}