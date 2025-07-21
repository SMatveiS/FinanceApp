package com.example.model

/**
 * Хранит список транзакций и их сумму
 */

data class TransactionsInfo(
    val transactions: List<Transaction>,
    val transactionsSum: Double,
    val currency: String
)