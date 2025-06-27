package com.example.myfinance.feature.domain.usecase

import com.example.myfinance.feature.domain.model.Transaction

/**
 * Хранит список транзакций и их сумму
 */

data class TransactionsResult(
    val transactions: List<Transaction>?,
    val transactionsSum: Double?
)
