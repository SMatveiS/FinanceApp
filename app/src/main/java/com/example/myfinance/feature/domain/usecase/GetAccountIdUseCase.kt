package com.example.myfinance.feature.domain.usecase

import com.example.myfinance.feature.domain.repository.AccountRepository
import com.example.myfinance.feature.utils.NetworkError
import javax.inject.Inject

class GetAccountIdUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke():  Result<Int> {
        val response = accountRepository.getAllAccounts()
        when {
            response.isSuccessful -> {
                val account = response.body()?.first()
                    ?: return Result.failure(
                        NetworkError(response.code(), message = "Нет доступных аккаунтов")
                    )
                val accountId = account.id ?: return Result.failure(
                    NetworkError(response.code(), message = "Нет id аккаунта")
                )
                return Result.success(accountId)
            }

            else -> return Result.failure(NetworkError(response.code(), response.message()))
        }
    }

//    suspend operator fun invoke(): Result<Int> = runCatching {
//        val response = accountRepository.getAllAccounts()
//        when {
//            !response.isSuccessful -> throw Exception("${response.code()} ${response.message()}")
//            response.body() == null -> throw Exception("Empty response body")
//            else -> {
//                val accounts = response.body()!!
//                accounts.firstOrNull()?.id
//                    ?: throw Exception("No accounts available")
//            }
//        }
//    }
}