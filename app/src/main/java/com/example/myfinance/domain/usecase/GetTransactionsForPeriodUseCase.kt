package com.example.myfinance.domain.usecase

import com.example.myfinance.domain.repository.TransactionRepository
import com.example.myfinance.data.utils.NetworkResult
import javax.inject.Inject

/**
 * Возвращает доходы или расходы и их сумму за определённый период
 *
 * Транзакции отсортированы по времени: чем новее, тем раньше в списке
 *
 * startDate: String - первый день периода (включительно)
 *
 * endDate: String - последний день периода (включительно)
 *
 * isIncomes: Boolean - если true, то возвращает доходы, иначе - расходы
 */

class GetTransactionsForPeriodUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val getAccountIdUseCase: GetAccountIdUseCase
) {

    suspend operator fun invoke(
        startDate: String,
        endDate: String,
        isIncomes: Boolean
    ): NetworkResult<TransactionsResult> {

        val accountIdResult = getAccountIdUseCase()
        return when (accountIdResult) {

            is NetworkResult.Error -> NetworkResult.Error(accountIdResult.errorMessage)

            is NetworkResult.Success -> {
                val transactionsResult = transactionRepository.getTransactionForPeriod(
                    id = accountIdResult.data,
                    startDate = startDate,
                    endDate = endDate
                )

                when (transactionsResult) {
                    is NetworkResult.Success ->  {
                        val transactions = transactionsResult.data.filter {
                            it.category.isIncome == isIncomes
                        }.sortedByDescending { it.date }

                        NetworkResult.Success(
                            TransactionsResult(
                                transactions = transactions,
                                transactionsSum = transactions.sumOf { it.amount }
                            )
                        )
                    }

                    is NetworkResult.Error -> NetworkResult.Error(transactionsResult.errorMessage)
                }
            }
        }
    }
}
