package com.example.myfinance.data

import com.example.myfinance.domain.model.Article
import com.example.myfinance.domain.model.Settings

object MockData {
    val articles = listOf(
        Article(id = 0, name = "Аренда квартиры", emoji = "\uD83C\uDFE1"),
        Article(id = 1, name = "Одежда", emoji = "\uD83D\uDC57"),
        Article(id = 2, name = "На собачку", emoji = "\uD83D\uDC36"),
        Article(id = 3, name = "На собачку", emoji = "\uD83D\uDC36"),
        Article(id = 4, name = "Ремонт квартиры", emoji = "РК"),
        Article(id = 5, name = "Продукты", emoji = "\uD83C\uDF6D"),
        Article(id = 6, name = "Спортзал", emoji = "\uD83C\uDFCB\uFE0F"),
        Article(id = 7, name = "Медицина", emoji = "\uD83D\uDC8A")
    )

    val settings = listOf(
        Settings(id = 0, name = "Основной цвет"),
        Settings(id = 1, name = "Звуки"),
        Settings(id = 2, name = "Хаптики"),
        Settings(id = 3, name = "Код пароль"),
        Settings(id = 4, name = "Синхронизация"),
        Settings(id = 5, name = "Язык"),
        Settings(id = 6, name = "О программе")
    )
}
