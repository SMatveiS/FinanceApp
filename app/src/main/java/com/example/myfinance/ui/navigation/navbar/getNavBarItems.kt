package com.example.myfinance.ui.navigation.navbar

import com.example.myfinance.R
import com.example.myfinance.ui.navigation.NavRoutes

/**
 * Возвращает список элементов навигационной панели
 */

fun getNavBarItems() = listOf(
        NavBarItem(
            title = "Расходы",
            icon = R.drawable.expenses,
            route = NavRoutes.Expenses
        ),
        NavBarItem(
            title = "Доходы",
            icon = R.drawable.incomes,
            route = NavRoutes.Incomes
        ),
        NavBarItem(
            title = "Счет",
            icon = R.drawable.account,
            route = NavRoutes.Account
        ),
        NavBarItem(
            title = "Статьи",
            icon = R.drawable.articles,
            route = NavRoutes.Categories
        ),
        NavBarItem(
            title = "Настройки",
            icon = R.drawable.settings,
            route = NavRoutes.Settings
        )
)
