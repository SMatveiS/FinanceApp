package com.example.myfinance.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myfinance.MockData.articles
import com.example.myfinance.MockData.expenses
import com.example.myfinance.MockData.incomes
import com.example.myfinance.MockData.settings
import com.example.myfinance.MockData.transactions
import com.example.myfinance.screens.AccountScreen
import com.example.myfinance.screens.ArticlesScreen
import com.example.myfinance.screens.ExpensesScreen
import com.example.myfinance.screens.IncomesScreen
import com.example.myfinance.screens.SettingsScreen
import com.example.myfinance.screens.TransactionsHistoryScreen

@Composable
fun FinappNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = NavRoutes.Expenses.route, modifier = modifier) {
        composable(NavRoutes.Expenses.route) { backStackEntry ->
            ExpensesScreen(
                expenses,
                onHistoryClicked = {
                    navController.navigate("${NavRoutes.TransactionsHistory.route}/${NavRoutes.Expenses.route}")
                }
            ) }
        composable(NavRoutes.Incomes.route) { backStackEntry ->
            IncomesScreen(
                incomes,
                onHistoryClicked = {
                    navController.navigate("${NavRoutes.TransactionsHistory.route}/${NavRoutes.Incomes.route}")
                }
            ) }
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