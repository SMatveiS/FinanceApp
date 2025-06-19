package com.example.myfinance.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("emoji")
    val emoji: String? = null,
    @SerialName("isIncome")
    val isIncome: Boolean? = null
    )