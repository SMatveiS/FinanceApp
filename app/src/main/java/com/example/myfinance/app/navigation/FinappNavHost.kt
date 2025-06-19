package com.example.myfinance.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myfinance.data.MockData.articles
import com.example.myfinance.data.MockData.expenses
import com.example.myfinance.data.MockData.incomes
import com.example.myfinance.data.MockData.settings
import com.example.myfinance.data.MockData.transactions
import com.example.myfinance.feature.presentation.screens.account.AccountScreen
import com.example.myfinance.feature.presentation.screens.articles.ArticlesScreen
import com.example.myfinance.feature.presentation.screens.expenses.ExpensesScreen
import com.example.myfinance.feature.presentation.screens.incomes.IncomesScreen
import com.example.myfinance.feature.presentation.screens.settings.SettingsScreen
import com.example.myfinance.feature.presentation.screens.transactionsHistory.TransactionsHistoryScreen

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
                expenses,
                onHistoryClicked = {
                    navController.navigate("${NavRoutes.TransactionsHistory.route}/${NavRoutes.Expenses.route}")
                }
            )
        }
        composable(NavRoutes.Incomes.route) { backStackEntry ->
            IncomesScreen(
                incomes,
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
                transactions = transactions,
                onBackArrowClicked = {
                    navController.navigate(source)
                }
            )
        }
    }
}