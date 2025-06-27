package com.example.myfinance.feature.domain.usecase

import com.example.myfinance.feature.domain.repository.TransactionRepository
import com.example.myfinance.feature.utils.NetworkResult
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
                if (accountIdResult.data != null) {
                    val transactionsResult = transactionRepository.getTransactionForPeriod(
                        id = accountIdResult.data,
                        startDate = startDate,
                        endDate = endDate
                    )

                    when (transactionsResult) {
                        is NetworkResult.Success ->  {
                            val transactions = transactionsResult.data?.filter {
                                it.category.isIncome == isIncomes
                            }?.sortedByDescending { it.date }

                            NetworkResult.Success(
                                TransactionsResult(
                                    transactions = transactions ?: emptyList(),
                                    transactionsSum = transactions?.sumOf { it.amount } ?: 0.0
                                )
                            )
                        }

                        is NetworkResult.Error -> NetworkResult.Error(transactionsResult.errorMessage)
                    }
                } else NetworkResult.Error(errorMessage = "Ошибка: Нет доступных аккаунтов")
            }
        }
    }
}
