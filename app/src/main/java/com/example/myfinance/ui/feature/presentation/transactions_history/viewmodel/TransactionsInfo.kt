package com.example.myfinance.ui.feature.presentation.transactions_history.viewmodel

import com.example.myfinance.domain.model.Transaction

/**
 * Хранит список транзакций и их сумму
 */

data class TransactionsInfo(
    val transactions: List<Transaction>,
    val transactionsSum: Double,
    val currency: String
)