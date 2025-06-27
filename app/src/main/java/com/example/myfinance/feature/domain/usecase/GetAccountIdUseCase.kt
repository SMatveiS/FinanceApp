package com.example.myfinance.feature.domain.usecase

import com.example.myfinance.feature.domain.repository.AccountRepository
import com.example.myfinance.feature.utils.NetworkResult
import com.example.myfinance.feature.utils.NetworkResult.*
import javax.inject.Inject

/**
 * Возвращает id счёта пользователя
 */

class GetAccountIdUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke():  NetworkResult<Int> {
        return when (val accounts = accountRepository.getAllAccounts()) {
            is Success ->  {
                if (accounts.data?.firstOrNull() != null) {
                    Success(accounts.data.first().id)
                } else Error(errorMessage = "Ошибка: Нет доступных аккаунтов")
            }

            is Error -> Error(accounts.errorMessage)
        }
    }
}
