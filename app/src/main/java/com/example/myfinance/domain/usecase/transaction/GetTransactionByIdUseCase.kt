package com.example.myfinance.domain.usecase.transaction

import com.example.myfinance.domain.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionByIdUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(id: Int) =
        transactionRepository.getTransaction(id = id)
}