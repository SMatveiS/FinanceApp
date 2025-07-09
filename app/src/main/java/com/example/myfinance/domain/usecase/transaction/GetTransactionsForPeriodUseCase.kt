package com.example.myfinance.domain.usecase.transaction

import com.example.myfinance.domain.repository.TransactionRepository
import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.data.utils.map
import com.example.myfinance.domain.usecase.account.GetAccountUseCase
import com.example.myfinance.ui.feature.presentation.transactions_history.viewmodel.TransactionsResult
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
    private val getAccountUseCase: GetAccountUseCase
) {

    suspend operator fun invoke(
        startDate: String,
        endDate: String,
        isIncomes: Boolean
    ): NetworkResult<TransactionsResult> {

        // Нельзя сделать через map, так как вернёт NetworkResult<NetworkResult<...>>
        return when (val accountResult = getAccountUseCase()) {
            is NetworkResult.Error -> NetworkResult.Error(errorMessage = accountResult.errorMessage)

            is NetworkResult.Success -> {
                val transactionsResult = transactionRepository.getTransactionForPeriod(
                    id = accountResult.data.id,
                    startDate = startDate,
                    endDate = endDate
                )

                transactionsResult.map { transactions ->
                    val sortedTransactions = transactions.filter {
                        it.category.isIncome == isIncomes
                    }.sortedByDescending { it.date }

                    TransactionsResult(
                        transactions = sortedTransactions,
                        transactionsSum = transactions.sumOf { it.amount },
                        currency = accountResult.data.currency
                    )
                }
            }
        }
    }
}
