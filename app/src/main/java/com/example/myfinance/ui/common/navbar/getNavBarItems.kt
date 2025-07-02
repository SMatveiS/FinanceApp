package com.example.myfinance.ui.common.navbar

import com.example.myfinance.R

/**
 * Возвращает список элементов навигационной панели
 */

fun getNavBarItems(): List<NavBarItem> {
    return listOf(
        NavBarItem(
            title = "Расходы",
            icon = R.drawable.expenses,
            route ="expenses"
        ),
        NavBarItem(
            title = "Доходы",
            icon = R.drawable.incomes,
            route = "incomes"
        ),
        NavBarItem(
            title = "Счет",
            icon = R.drawable.account,
            route = "account"
        ),
        NavBarItem(
            title = "Статьи",
            icon = R.drawable.articles,
            route = "articles"
        ),
        NavBarItem(
            title = "Настройки",
            icon = R.drawable.settings,
            route = "settings"
        )
    )
}
