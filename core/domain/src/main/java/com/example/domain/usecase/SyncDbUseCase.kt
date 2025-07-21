package com.example.domain.usecase

import com.example.data.repository.external.AccountRepository
import com.example.data.repository.external.CategoryRepository
import com.example.data.repository.external.TransactionRepository
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Синхронизирует данные с сервера с локальными данными (локальные данные принимают значения данных с сервера)
 */

class SyncDbUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository,
    private val accountRepository: AccountRepository
) {

    suspend operator fun invoke(): Result<Unit> {
        val accountResult = accountRepository.syncAccount()

        // Нельзя сделать через map, так как вернёт Result<Result<...>>
        return accountResult.fold(

            onFailure =  { error -> Result.failure(error) },

            onSuccess = { account ->
                val categoriesResult = categoryRepository.syncCategories()

                categoriesResult.fold(
                    onFailure =  { error -> Result.failure(error) },

                    onSuccess = {
                        val transactionsResult = transactionRepository.syncTransactions(
                            id = account.id,
                            startDate = OffsetDateTime.parse(account.createdAt)
                                .format(DateTimeFormatter.ofPattern("y-MM-dd")),
                            endDate = LocalDate.now().format(DateTimeFormatter.ofPattern("y-MM-dd"))
                        )

                        transactionsResult
                    }
                )

            }
        )
    }

}