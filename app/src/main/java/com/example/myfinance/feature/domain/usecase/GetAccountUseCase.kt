package com.example.myfinance.feature.domain.usecase

import com.example.myfinance.feature.domain.model.Account
import com.example.myfinance.feature.domain.repository.AccountRepository
import com.example.myfinance.feature.utils.NetworkResult
import com.example.myfinance.feature.utils.NetworkResult.Error
import com.example.myfinance.feature.utils.NetworkResult.Success
import javax.inject.Inject

/**
 * Возвращает счёт пользователя
 */

class GetAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke():  NetworkResult<Account> {
        return when (val accounts = accountRepository.getAllAccounts()) {
            is Success ->  {
                if (accounts.data?.firstOrNull() != null) {
                    Success(accounts.data.first())
                } else Error(errorMessage = "Ошибка: Нет доступных аккаунтов")
            }

            is Error -> Error(accounts.errorMessage)
        }
    }
}