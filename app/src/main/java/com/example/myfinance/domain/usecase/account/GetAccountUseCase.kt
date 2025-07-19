package com.example.myfinance.domain.usecase.account

import com.example.myfinance.domain.model.Account
import com.example.myfinance.domain.repository.AccountRepository
import javax.inject.Inject

/**
 * Возвращает счёт пользователя
 */

class GetAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke():  Result<Account> = accountRepository.getAccount()
}