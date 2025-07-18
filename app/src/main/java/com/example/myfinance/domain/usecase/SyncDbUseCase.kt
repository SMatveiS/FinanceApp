package com.example.myfinance.domain.usecase

import com.example.myfinance.domain.repository.CategoryRepository
import com.example.myfinance.domain.repository.TransactionRepository
import com.example.myfinance.domain.usecase.account.GetAccountUseCase
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class SyncDbUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository,
    private val getAccountUseCase: GetAccountUseCase
) {

    suspend operator fun invoke(): Result<Unit> {
        val accountResult = getAccountUseCase()

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