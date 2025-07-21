package com.example.domain.usecase.transaction

import com.example.data.repository.external.TransactionRepository
import com.example.model.Transaction
import javax.inject.Inject

class UpdateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(transaction: Transaction) =
        transactionRepository.updateTransaction(transaction.id, transaction)
}