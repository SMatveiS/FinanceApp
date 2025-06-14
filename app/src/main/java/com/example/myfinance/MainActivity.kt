package com.example.myfinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.screens.AccountScreen
import com.example.myfinance.screens.ArticlesScreen
import com.example.myfinance.screens.ExpensesScreen
import com.example.myfinance.screens.IncomesScreen
import com.example.myfinance.screens.SettingsScreen
import com.example.myfinance.ui.theme.MyFinanceTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFinanceTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val currentRoute = currentDestination?.route

                // Конфигурация верхней панели для каждого экрана
                val topBarConfigs = mapOf(
                    NavRoutes.Expenses.route to "Расходы сегодня",
                    NavRoutes.Incomes.route to "Доходы сегодня",
                    NavRoutes.Account.route to "Мой счет",
                    NavRoutes.Articles.route to "Мои статьи",
                    NavRoutes.Settings.route to "Настройки"
                )
                // Текущая конфигурация
                val currentConfig = topBarConfigs[currentRoute] ?: "Расходы сегодня"

                // Список элементов навигационной панели
                val navBarItems = listOf(
                    BarItem(
                        title = "Расходы",
                        icon = ImageVector.vectorResource(R.drawable.expenses),
                        route ="expenses"
                    ),
                    BarItem(
                        title = "Доходы",
                        icon = ImageVector.vectorResource(R.drawable.incomes),
                        route = "incomes"
                    ),
                    BarItem(
                        title = "Счет",
                        icon = ImageVector.vectorResource(R.drawable.account),
                        route = "account"
                    ),
                    BarItem(
                        title = "Статьи",
                        icon = ImageVector.vectorResource(R.drawable.articles),
                        route = "articles"
                    ),
                    BarItem(
                        title = "Настройки",
                        icon = ImageVector.vectorResource(R.drawable.settings),
                        route = "settings"
                    )
                )

                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    //Верхняя панель
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text(currentConfig, fontSize = 22.sp) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            actions = {
                                if (currentRoute == NavRoutes.Expenses.route || currentRoute == NavRoutes.Incomes.route) {
                                    IconButton(onClick = { /* Действие */ }) {
                                        Icon(
                                            ImageVector.vectorResource(R.drawable.history),
                                            contentDescription = "История",
                                            modifier = Modifier.size(24.dp),
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                } else if (currentRoute == NavRoutes.Account.route) {
                                    IconButton(onClick = { /* Действие */ }) {
                                        Icon(
                                            ImageVector.vectorResource(R.drawable.edit),
                                            contentDescription = "История",
                                            modifier = Modifier.size(24.dp),
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        )
                    },
                    // Навигационная панель
                    bottomBar = {
                        NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                            navBarItems.forEach { navItem ->
                                NavigationBarItem(
                                    icon = { Icon(
                                        navItem.icon,
                                        contentDescription = navItem.title,
                                        modifier = Modifier.size(24.dp),
                                    ) },
                                    label = { Text(navItem.title, fontSize = 12.sp) },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.onSurface,
                                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        indicatorColor = MaterialTheme.colorScheme.secondary    // Фон выбранного элемента
                                    ),
                                    selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                                    onClick = {
                                        navController.navigate(navItem.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    },
                    floatingActionButton = {
                        if (currentRoute == NavRoutes.Expenses.route || currentRoute == NavRoutes.Incomes.route) {
                            FloatingActionButton(
                                onClick = { /* Действие */ },
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.background,
                                shape = CircleShape
                            ) {
                                Icon(
                                    ImageVector.vectorResource(R.drawable.plus_for_add_button),
                                    contentDescription = "Добавить",
                                    modifier = Modifier.size(15.56.dp)
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    val expenses = remember {
                        listOf(
                            Expense(id = 0, category = "Аренда квартиры", amount = 100000, emoji = "\uD83C\uDFE1"),
                            Expense(id = 1, category = "Одежда", amount = 100000, emoji = "\uD83D\uDC57"),
                            Expense(id = 2, category = "На собачку", comment = "Джек", amount = 100000, emoji = "\uD83D\uDC36"),
                            Expense(id = 3, category = "На собачку", comment = "Энни", amount = 100000, emoji = "\uD83D\uDC36"),
                            Expense(id = 4, category = "Ремонт квартиры", amount = 100000, emoji = "РК"),
                            Expense(id = 5, category = "Продукты", amount = 100000, emoji = "\uD83C\uDF6D"),
                            Expense(id = 6, category = "Спортзал", amount = 100000, emoji = "\uD83C\uDFCB\uFE0F"),
                            Expense(id = 7, category = "Медицина", amount = 100000, emoji = "\uD83D\uDC8A")
                        )
                    }
                    val incomes = remember {
                        listOf(
                            Income(id = 0, category = "Зарплата", 500000),
                            Income(id = 1, category = "Подработка", 100000)
                        )
                    }
                    val articles = remember {
                        listOf(
                            Article(id = 0, name = "Аренда квартиры", emoji = "\uD83C\uDFE1"),
                            Article(id = 1, name = "Одежда", emoji = "\uD83D\uDC57"),
                            Article(id = 2, name = "На собачку", emoji = "\uD83D\uDC36"),
                            Article(id = 3, name = "На собачку", emoji = "\uD83D\uDC36"),
                            Article(id = 4, name = "Ремонт квартиры", emoji = "РК"),
                            Article(id = 5, name = "Продукты", emoji = "\uD83C\uDF6D"),
                            Article(id = 6, name = "Спортзал", emoji = "\uD83C\uDFCB\uFE0F"),
                            Article(id = 7, name = "Медицина", emoji = "\uD83D\uDC8A")
                        )
                    }
                    val settings = remember {
                        listOf(
                            Settings(id = 0, name = "Основной цвет"),
                            Settings(id = 1, name = "Звуки"),
                            Settings(id = 2, name = "Хаптики"),
                            Settings(id = 3, name = "Код пароль"),
                            Settings(id = 4, name = "Синхронизация"),
                            Settings(id = 5, name = "Язык"),
                            Settings(id = 6, name = "О программе")
                        )
                    }

                    NavHost(navController, startDestination = NavRoutes.Expenses.route, Modifier.padding(innerPadding)) {
                        composable(NavRoutes.Expenses.route) { ExpensesScreen(expenses) }
                        composable(NavRoutes.Incomes.route) { IncomesScreen(incomes) }
                        composable(NavRoutes.Account.route) { AccountScreen() }
                        composable(NavRoutes.Articles.route) { ArticlesScreen(articles) }
                        composable(NavRoutes.Settings.route) { SettingsScreen(settings) }
                    }
                }
            }
        }
    }
}

// Элемент навигационной панели
data class BarItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

sealed class NavRoutes(val route: String) {
    object Expenses : NavRoutes("expenses")
    object Incomes : NavRoutes("incomes")
    object Account : NavRoutes("account")
    object Articles : NavRoutes("articles")
    object Settings : NavRoutes("settings")
}