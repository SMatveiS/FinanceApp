package com.example.myfinance.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myfinance.R

@Composable
fun FinappNavBar(navController: NavHostController) {
    // Список элементов навигационной панели
    val navBarItems = remember {
        listOf(
            BarItem(
                title = "Расходы",
                icon = R.drawable.expenses,
                route ="expenses"
            ),
            BarItem(
                title = "Доходы",
                icon = R.drawable.incomes,
                route = "incomes"
            ),
            BarItem(
                title = "Счет",
                icon = R.drawable.account,
                route = "account"
            ),
            BarItem(
                title = "Статьи",
                icon = R.drawable.articles,
                route = "articles"
            ),
            BarItem(
                title = "Настройки",
                icon = R.drawable.settings,
                route = "settings"
            )
        )
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val source = navBackStackEntry?.arguments?.getString("source")

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        navBarItems.forEach { navItem ->
            NavigationBarItem(
                icon = {
                    Icon(
                        ImageVector.vectorResource(navItem.icon),
                        contentDescription = navItem.title,
                        modifier = Modifier.size(24.dp),
                    )
                },
                label = { Text(navItem.title, fontSize = 12.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.secondary    // Фон выбранного элемента
                ),
                selected = currentDestination?.hierarchy?.any { it.route == navItem.route || navItem.route == source } == true,
                onClick = {
                    if (navItem.route != currentDestination?.route) {
                        navController.navigate(navItem.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                        }
                    }
                }
            )
        }
    }
}

// Элемент навигационной панели
data class BarItem(
    val title: String,
    val icon: Int,
    val route: String
)

sealed class NavRoutes(val route: String) {
    object Expenses : NavRoutes("expenses")
    object Incomes : NavRoutes("incomes")
    object Account : NavRoutes("account")
    object Articles : NavRoutes("articles")
    object Settings : NavRoutes("settings")
    object TransactionsHistory : NavRoutes("history")
}