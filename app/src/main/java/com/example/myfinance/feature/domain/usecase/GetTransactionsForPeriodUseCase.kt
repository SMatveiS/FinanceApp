package com.example.myfinance.feature.domain.usecase

import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.feature.domain.repository.TransactionRepository
import com.example.myfinance.feature.utils.NetworkResult
import javax.inject.Inject

class GetTransactionsForPeriodUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val getAccountIdUseCase: GetAccountIdUseCase
) {

    suspend operator fun invoke(
        startDate: String,
        endDate: String
    ): NetworkResult<List<Transaction>> {

        val accountIdResult = getAccountIdUseCase()
        return when (accountIdResult) {
            is NetworkResult.Success -> {
                val transactions = transactionRepository.getTransactionForPeriod(
                    id = accountIdResult.data!!,
                    startDate = startDate,
                    endDate = endDate
                )

                when (transactions) {
                    is NetworkResult.Success -> NetworkResult.Success(transactions.data)

                    is NetworkResult.Error -> NetworkResult.Error(transactions.errorMessage)

                    is NetworkResult.Loading -> NetworkResult.Loading()
                }
            }

            is NetworkResult.Error -> NetworkResult.Error(accountIdResult.errorMessage)

            is NetworkResult.Loading -> NetworkResult.Loading()
        }
    }
}
