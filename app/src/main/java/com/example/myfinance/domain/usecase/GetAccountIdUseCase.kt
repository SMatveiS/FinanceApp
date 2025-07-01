package com.example.myfinance.domain.usecase

import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.data.utils.NetworkResult.*
import javax.inject.Inject

/**
 * Возвращает id счёта пользователя
 */

class GetAccountIdUseCase @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase
) {
    suspend operator fun invoke():  NetworkResult<Int> {
        return when (val account = getAccountUseCase()) {
            is Success -> Success(account.data.id)
            is Error -> Error(account.errorMessage)
        }
    }
}
