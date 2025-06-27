package com.example.myfinance.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myfinance.data.MockData.articles
import com.example.myfinance.data.MockData.settings
import com.example.myfinance.feature.presentation.account.AccountScreen
import com.example.myfinance.feature.presentation.articles.ArticlesScreen
import com.example.myfinance.feature.presentation.expenses.ExpensesScreen
import com.example.myfinance.feature.presentation.incomes.IncomesScreen
import com.example.myfinance.feature.presentation.settings.SettingsScreen
import com.example.myfinance.feature.presentation.transactionsHistory.TransactionsHistoryScreen

sealed class NavRoutes(val route: String) {
    object Expenses : NavRoutes("expenses")
    object Incomes : NavRoutes("incomes")
    object Account : NavRoutes("account")
    object Articles : NavRoutes("articles")
    object Settings : NavRoutes("settings")
    object TransactionsHistory : NavRoutes("history")
}

@Composable
fun FinappNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = NavRoutes.Expenses.route, modifier = modifier) {
        composable(NavRoutes.Expenses.route) { backStackEntry ->
            ExpensesScreen(
                onHistoryClicked = {
                    navController.navigate("${NavRoutes.TransactionsHistory.route}/${NavRoutes.Expenses.route}")
                }
            )
        }
        composable(NavRoutes.Incomes.route) { backStackEntry ->
            IncomesScreen(
                onHistoryClicked = {
                    navController.navigate("${NavRoutes.TransactionsHistory.route}/${NavRoutes.Incomes.route}")
                }
            )
        }
        composable(NavRoutes.Account.route) { AccountScreen() }
        composable(NavRoutes.Articles.route) { ArticlesScreen(articles) }
        composable(NavRoutes.Settings.route) { SettingsScreen(settings) }
        composable(
            "${NavRoutes.TransactionsHistory.route}/{source}",
            arguments = listOf(navArgument("source") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val source = backStackEntry.arguments?.getString("source") ?: NavRoutes.Expenses.route
            TransactionsHistoryScreen(
                onBackArrowClicked = {
                    navController.navigate(source)
                }
            )
        }
    }
}
