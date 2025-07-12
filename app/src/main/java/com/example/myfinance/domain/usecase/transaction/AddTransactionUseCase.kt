package com.example.myfinance.domain.usecase.transaction

import com.example.myfinance.domain.model.Transaction
import com.example.myfinance.domain.repository.TransactionRepository
import javax.inject.Inject

class AddTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(transaction: Transaction) =
        transactionRepository.addTransaction(transaction)
}
