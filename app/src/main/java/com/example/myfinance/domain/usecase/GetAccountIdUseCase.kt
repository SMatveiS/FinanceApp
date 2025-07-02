package com.example.myfinance.domain.usecase

import com.example.myfinance.data.utils.map
import javax.inject.Inject

/**
 * Возвращает id счёта пользователя
 */

class GetAccountIdUseCase @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase
) {
    suspend operator fun invoke() = getAccountUseCase().map { it.id }
}
