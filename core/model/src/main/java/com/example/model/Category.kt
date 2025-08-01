package com.example.model

/**
 * Доменная модель статьи
 */

data class Category(
    val id: Int,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
)
