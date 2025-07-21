package com.example.domain.usecase.account

import com.example.data.repository.external.AccountRepository
import com.example.model.Account
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