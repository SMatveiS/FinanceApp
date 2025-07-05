package com.example.myfinance.domain.usecase

import com.example.myfinance.domain.model.Account
import com.example.myfinance.domain.repository.AccountRepository
import javax.inject.Inject

/**
 * Обновляет данные о счёте
 */

class UpdateAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke(account: Account) {
        accountRepository.updateAccount(account.id, account)
    }
}