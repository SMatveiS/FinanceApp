package com.example.myfinance.data.api.transaction

import com.example.myfinance.data.model.TransactionDto
import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.feature.domain.repository.TransactionRepository
import com.example.myfinance.feature.utils.BaseApiResponse
import com.example.myfinance.feature.utils.NetworkResult
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Возвращает информацию о транзакциях внутри NetworkResult независимо от источника
 */

class TransactionRepositoryImpl @Inject constructor(
    private val transactionRemoteDataSource: TransactionRemoteDataSource
): TransactionRepository, BaseApiResponse() {

    override suspend fun getTransaction(id: Int) =
        transactionRemoteDataSource.getTransaction(id = id)

    override suspend fun addTransaction(transaction: TransactionDto) =
        transactionRemoteDataSource.addTransaction(transaction = transaction)

    override suspend fun updateTransaction(id: Int, transaction: TransactionDto) =
        transactionRemoteDataSource.updateTransaction(id = id, transaction = transaction)

    override suspend fun deleteTransaction(id: Int) =
        transactionRemoteDataSource.deleteTransaction(id = id)

    override suspend fun getTransactionForPeriod(
        id: Int,
        startDate: String,
        endDate: String
    ): NetworkResult<List<Transaction>> {

        val transactions = safeApiCall {
            transactionRemoteDataSource.getTransactionsForPeriod(
                id = id,
                startDate = startDate,
                endDate = endDate
            )
        }

        return when (transactions) {
            is NetworkResult.Success ->
                NetworkResult.Success(transactions.data?.map { it.toDomain() })

            is NetworkResult.Error -> NetworkResult.Error(errorMessage = transactions.errorMessage)
        }

    }

    private fun TransactionDto.toDomain() = Transaction(
        id = id,
        accountId = account.id,
        category = category,
        amount = amount.toDouble(),
        date = OffsetDateTime.parse(transactionDate)
            .format(DateTimeFormatter.ofPattern("dd.MM.yy HH:mm")),
        comment = if (comment == "") null else comment
    )

}
