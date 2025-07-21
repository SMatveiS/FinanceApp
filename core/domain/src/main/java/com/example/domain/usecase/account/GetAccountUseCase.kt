package com.example.domain.usecase.account

import com.example.data.repository.external.AccountRepository
import com.example.model.Account
import javax.inject.Inject

/**
 * Возвращает счёт пользователя
 */

class GetAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke():  Result<Account> = accountRepository.getAccount()
}