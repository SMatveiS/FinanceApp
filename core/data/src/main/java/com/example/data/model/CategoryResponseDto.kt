package com.example.data.model

import com.example.data.local.database.CategoryEntity
import com.example.model.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Модель для получения информации о категории с сервера
 */

@Serializable
data class CategoryResponseDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("emoji")
    val emoji: String = "",
    @SerialName("isIncome")
    val isIncome: Boolean = false
) {

    fun toDomain() = Category(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )

    fun toEntity() = CategoryEntity(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )
}
