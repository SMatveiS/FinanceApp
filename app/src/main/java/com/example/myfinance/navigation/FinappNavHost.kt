package com.example.myfinance.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myfinance.MockData.articles
import com.example.myfinance.MockData.expenses
import com.example.myfinance.MockData.incomes
import com.example.myfinance.MockData.settings
import com.example.myfinance.screens.AccountScreen
import com.example.myfinance.screens.ArticlesScreen
import com.example.myfinance.screens.ExpensesScreen
import com.example.myfinance.screens.IncomesScreen
import com.example.myfinance.screens.SettingsScreen

@Composable
fun FinappNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = NavRoutes.Expenses.route, modifier = modifier) {
        composable(NavRoutes.Expenses.route) { ExpensesScreen(expenses) }
        composable(NavRoutes.Incomes.route) { IncomesScreen(incomes) }
        composable(NavRoutes.Account.route) { AccountScreen() }
        composable(NavRoutes.Articles.route) { ArticlesScreen(articles) }
        composable(NavRoutes.Settings.route) { SettingsScreen(settings) }
    }
}