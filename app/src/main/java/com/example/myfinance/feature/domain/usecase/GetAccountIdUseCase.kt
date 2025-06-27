package com.example.myfinance.feature.domain.usecase

import com.example.myfinance.feature.utils.NetworkResult
import com.example.myfinance.feature.utils.NetworkResult.*
import javax.inject.Inject

/**
 * Возвращает id счёта пользователя
 */

class GetAccountIdUseCase @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase
) {
    suspend operator fun invoke():  NetworkResult<Int> {
        return when (val account = getAccountUseCase()) {
            is Success ->  {
                if (account.data != null) {
                    Success(account.data.id)
                } else Error(errorMessage = "Ошибка: Нет доступных аккаунтов")
            }

            is Error -> Error(account.errorMessage)
        }
    }
}
