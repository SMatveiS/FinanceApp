package com.example.myfinance.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.myfinance.data.MockData.settings
import com.example.myfinance.ui.feature.presentation.account.screen.AccountScreen
import com.example.myfinance.ui.feature.presentation.account.screen.edit_account_screen.EditAccountScreen
import com.example.myfinance.ui.feature.presentation.categories.screen.CategoryScreen
import com.example.myfinance.ui.feature.presentation.change_transaction.screen.ChangeTransactionScreen
import com.example.myfinance.ui.feature.presentation.expenses.screen.ExpensesScreen
import com.example.myfinance.ui.feature.presentation.incomes.screen.IncomesScreen
import com.example.myfinance.ui.feature.presentation.settings.SettingsScreen
import com.example.myfinance.ui.feature.presentation.transactions_history.screen.TransactionsHistoryScreen

@Composable
fun FinappNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = NavRoutes.Expenses, modifier = modifier) {

        navigation<NavRoutes.Expenses>(startDestination = NavRoutes.Transactions) {
            composable<NavRoutes.Transactions> { backStackEntry ->
                ExpensesScreen(
                    onHistoryClicked = {
                        navController.navigate(route = NavRoutes.TransactionsHistory)
                    },

                    onFabClicked = {
                        navController.navigate(route = NavRoutes.ChangeTransaction)
                    },
                )
            }

            composable<NavRoutes.TransactionsHistory> { backStackEntry ->
                TransactionsHistoryScreen(
                    onBackArrowClicked = {
                        navController.navigate(route = NavRoutes.Transactions)
                    }
                )
            }

            composable<NavRoutes.ChangeTransaction> { backStackEntry ->
                ChangeTransactionScreen(
                    returnToPreviousScreen = {
                        navController.navigate(route = NavRoutes.Transactions)
                    }
                )
            }
        }

        navigation<NavRoutes.Incomes>(startDestination = NavRoutes.Transactions) {
            composable<NavRoutes.Transactions> { backStackEntry ->
                IncomesScreen (
                    onHistoryClicked = {
                        navController.navigate(route = NavRoutes.TransactionsHistory)
                    },

                    onFabClicked = {
                        navController.navigate(route = NavRoutes.ChangeTransaction)
                    },
                )
            }

            composable<NavRoutes.TransactionsHistory> { backStackEntry ->
                TransactionsHistoryScreen(
                    onBackArrowClicked = {
                        navController.navigate(route = NavRoutes.Transactions)
                    }
                )
            }

            composable<NavRoutes.ChangeTransaction> { backStackEntry ->
                ChangeTransactionScreen(
                    returnToPreviousScreen = {
                        navController.navigate(route = NavRoutes.Transactions)
                    }
                )
            }
        }

        navigation<NavRoutes.Account>(startDestination = NavRoutes.AccountStatistic) {
            composable<NavRoutes.AccountStatistic> {
                AccountScreen(
                    onEditAccountClicked = {
                        navController.navigate(route = NavRoutes.EditAccount)
                    }
                )
            }

            composable<NavRoutes.EditAccount> {
                EditAccountScreen(
                    returnToAccountScreen = { navController.navigate(route = NavRoutes.Account) }
                )
            }
        }


        composable<NavRoutes.Categories> { CategoryScreen() }

        composable<NavRoutes.Settings> { SettingsScreen(settings) }

    }
}
