package com.example.domain.usecase.transaction

import com.example.data.repository.external.TransactionRepository
import com.example.model.TransactionsInfo
import com.example.domain.usecase.account.GetAccountUseCase
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
    ): Result<TransactionsInfo> {

        val account = getAccountUseCase().getOrElse {
            return Result.failure(it)
        }

        val transactionsResult = transactionRepository.getTransactionForPeriod(
            id = account.id,
            startDate = startDate,
            endDate = endDate
        )

        return transactionsResult.map { transactions ->
            val sortedTransactions = transactions.filter {
                it.category.isIncome == isIncomes
            }.sortedByDescending { it.date }

            TransactionsInfo(
                transactions = sortedTransactions,
                transactionsSum = sortedTransactions.sumOf { it.amount },
                currency = account.currency
            )
        }
    }
}
