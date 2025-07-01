package com.example.myfinance.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Модель для получения информации о категории с сервера
 * Для бизнес-логики модель была бы такой же, поэтому используется и в качестве доменной модели
 */

@Serializable
data class CategoryDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("emoji")
    val emoji: String = "",
    @SerialName("isIncome")
    val isIncome: Boolean = false
) {

}
