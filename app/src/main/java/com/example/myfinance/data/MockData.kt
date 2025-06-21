package com.example.myfinance.data

import com.example.myfinance.feature.domain.model.Article
import com.example.myfinance.feature.domain.model.Category
import com.example.myfinance.feature.domain.model.Settings
import com.example.myfinance.feature.domain.model.Transaction

object MockData {
    val expenses = listOf(
        Category(
            id = 0,
            category = "Аренда квартиры",
            amount = 100000.0,
            emoji = "\uD83C\uDFE1",
            isIncome = false
        ),
        Category(
            id = 1,
            category = "Одежда",
            amount = 100000.0,
            emoji = "\uD83D\uDC57",
            isIncome = false
        ),
        Category(
            id = 2,
            category = "На собачку",
            comment = "Джек",
            amount = 100000.0,
            emoji = "\uD83D\uDC36",
            isIncome = false
        ),
        Category(
            id = 3,
            category = "На собачку",
            comment = "Энни",
            amount = 100000.0,
            emoji = "\uD83D\uDC36",
            isIncome = false
        ),
        Category(
            id = 4,
            category = "Ремонт квартиры",
            amount = 100000.0,
            emoji = "РК",
            isIncome = false
        ),
        Category(
            id = 5,
            category = "Продукты",
            amount = 100000.0,
            emoji = "\uD83C\uDF6D",
            isIncome = false
        ),
        Category(
            id = 6,
            category = "Спортзал",
            amount = 100000.0,
            emoji = "\uD83C\uDFCB\uFE0F",
            isIncome = false
        ),
        Category(
            id = 7,
            category = "Медицина",
            amount = 100000.0,
            emoji = "\uD83D\uDC8A",
            isIncome = false
        )
    )

    val incomes = listOf(
        Category(id = 0, category = "Зарплата", 500000.0, isIncome = true),
        Category(id = 1, category = "Подработка", 100000.0, isIncome = true)
    )

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

    val transactions = listOf(
        Transaction(
            id = 0,
            category = "Аренда квартиры",
            amount = 100000,
            emoji = "\uD83C\uDFE1",
            date = "12.01.2012"
        ),
        Transaction(
            id = 1,
            category = "Одежда",
            amount = 100000,
            emoji = "\uD83D\uDC57",
            date = "12.01.2012"
        )
    )
}