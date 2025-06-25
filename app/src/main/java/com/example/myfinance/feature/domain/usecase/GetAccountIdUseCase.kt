package com.example.myfinance.feature.domain.usecase

import com.example.myfinance.data.api.account.AccountRepository

class GetAccountIdUseCase(
    private val accountRepository: AccountRepository
) {
//    suspend operator fun invoke() = accountRepository.getAllAccounts()

    suspend operator fun invoke(): Result<Int> = runCatching {
        val response = accountRepository.getAllAccounts()
        when {
            !response.isSuccessful -> throw Exception("${response.code()} ${response.message()}")
            response.body() == null -> throw Exception("Empty response body")
            else -> {
                val accounts = response.body()!!
                accounts.firstOrNull()?.id
                    ?: throw Exception("No accounts available")
            }
        }
    }
}