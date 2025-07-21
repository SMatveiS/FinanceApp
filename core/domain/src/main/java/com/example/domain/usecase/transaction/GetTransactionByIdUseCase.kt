package com.example.domain.usecase.transaction

import com.example.data.repository.external.TransactionRepository
import javax.inject.Inject

class GetTransactionByIdUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(id: Int) =
        transactionRepository.getTransaction(id = id)
}