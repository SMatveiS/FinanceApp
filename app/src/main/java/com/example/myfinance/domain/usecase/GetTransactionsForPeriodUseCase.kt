package com.example.myfinance.domain.usecase

import com.example.myfinance.domain.repository.TransactionRepository
import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.data.utils.map
import com.example.myfinance.ui.feature.presentation.transactionsHistory.viewmodel.TransactionsResult
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

        // Нельзя сделать через map, так как вернёт NetworkResult<NetworkResult<...>>
        return when (val accountIdResult = getAccountIdUseCase()) {
            is NetworkResult.Error -> NetworkResult.Error(errorMessage = accountIdResult.errorMessage)

            is NetworkResult.Success -> {
                val transactionsResult = transactionRepository.getTransactionForPeriod(
                    id = accountIdResult.data,
                    startDate = startDate,
                    endDate = endDate
                )

                transactionsResult.map { transactions ->
                    val sortedTransactions = transactions.filter {
                        it.category.isIncome == isIncomes
                    }.sortedByDescending { it.date }

                    TransactionsResult(
                        transactions = sortedTransactions,
                        transactionsSum = transactions.sumOf { it.amount }
                    )
                }
            }
        }
    }
}
