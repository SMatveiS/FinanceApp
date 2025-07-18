package com.example.myfinance.domain.usecase.transaction

import com.example.myfinance.domain.repository.TransactionRepository
import com.example.myfinance.domain.usecase.account.GetAccountUseCase
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class SyncTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val getAccountUseCase: GetAccountUseCase
) {

    suspend operator fun invoke(): Result<Unit> {
        val accountResult = getAccountUseCase()

        // Нельзя сделать через map, так как вернёт Result<Result<...>>
        return accountResult.fold(

            onFailure =  { error -> Result.failure(error) },

            onSuccess = { account ->
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

}