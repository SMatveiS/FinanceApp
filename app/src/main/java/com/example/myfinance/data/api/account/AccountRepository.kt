package com.example.myfinance.data.api.account

import com.example.myfinance.data.model.AccountDto
import com.example.myfinance.feature.utils.BaseApiResponse
import com.example.myfinance.feature.utils.NetworkResult
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource
) {

    //    suspend fun getAllAccounts(): NetworkResult<List<AccountDto>> {
//        return safeApiCall { accountRemoteDataSource.getAllAccounts() }
//    }
    suspend fun getAllAccounts() = accountRemoteDataSource.getAllAccounts()

}