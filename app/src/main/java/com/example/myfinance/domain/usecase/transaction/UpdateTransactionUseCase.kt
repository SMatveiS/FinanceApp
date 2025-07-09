package com.example.myfinance.domain.usecase.transaction

import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.domain.repository.TransactionRepository
import javax.inject.Inject

class UpdateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(transaction: Transaction) =
        transactionRepository.updateTransaction(transaction.id, transaction)
}