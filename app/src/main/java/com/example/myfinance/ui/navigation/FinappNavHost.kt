package com.example.myfinance.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myfinance.data.MockData.settings
import com.example.myfinance.ui.feature.presentation.account.screen.AccountScreen
import com.example.myfinance.ui.feature.presentation.account.screen.edit_account_screen.EditAccountScreen
import com.example.myfinance.ui.feature.presentation.category.screen.CategoryScreen
import com.example.myfinance.ui.feature.presentation.expenses.screen.ExpensesScreen
import com.example.myfinance.ui.feature.presentation.incomes.screen.IncomesScreen
import com.example.myfinance.ui.feature.presentation.settings.SettingsScreen
import com.example.myfinance.ui.feature.presentation.transactionsHistory.screen.TransactionsHistoryScreen

@Composable
fun FinappNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = NavRoutes.Expenses.route, modifier = modifier) {

        composable(NavRoutes.Expenses.route) { backStackEntry ->
            ExpensesScreen(
                onHistoryClicked = {
                    navController.navigate("${NavRoutes.TransactionsHistory.route}/${NavRoutes.Expenses.route}")
                }
            ) }

        composable(NavRoutes.Incomes.route) { backStackEntry ->
            IncomesScreen(
                onHistoryClicked = {
                    navController.navigate("${NavRoutes.TransactionsHistory.route}/${NavRoutes.Incomes.route}")
                }
            ) }

        composable(NavRoutes.Account.route) {
            AccountScreen(
                onEditAccountClicked = {
                    navController.navigate("${NavRoutes.EditAccount.route}/${NavRoutes.Account.route}")
                }
            ) }

        composable(
            NavRoutes.EditAccount.route + "/{source}"
        ) {
            EditAccountScreen(
                returnToAccountScreen = { navController.navigate(NavRoutes.Account.route)}
            ) }

        composable(NavRoutes.Articles.route) { CategoryScreen() }

        composable(NavRoutes.Settings.route) { SettingsScreen(settings) }

        composable(
            NavRoutes.TransactionsHistory.route + "/{source}"
        ) { backStackEntry ->
            val source = backStackEntry.arguments?.getString("source") ?: NavRoutes.Expenses.route
            TransactionsHistoryScreen(
                onBackArrowClicked = {
                    navController.navigate(source)
                }
            ) }
    }
}
