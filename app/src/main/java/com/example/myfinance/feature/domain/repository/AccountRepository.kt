package com.example.myfinance.feature.domain.repository

import com.example.myfinance.feature.domain.model.Account
import com.example.myfinance.feature.utils.NetworkResult

interface AccountRepository {

    suspend fun getAllAccounts(): NetworkResult<List<Account>>

}
