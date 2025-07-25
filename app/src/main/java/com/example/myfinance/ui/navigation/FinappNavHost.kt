package com.example.myfinance.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.data.MockData.settings
import com.example.myfinance.ui.feature.presentation.account.screen.AccountScreen
import com.example.myfinance.ui.feature.presentation.account.screen.edit_account_screen.EditAccountScreen
import com.example.myfinance.ui.feature.presentation.analysis.screen.AnalysisScreen
import com.example.myfinance.ui.feature.presentation.categories.screen.CategoryScreen
import com.example.myfinance.ui.feature.presentation.change_transaction.screen.ChangeTransactionScreen
import com.example.myfinance.ui.feature.presentation.expenses.screen.ExpensesScreen
import com.example.myfinance.ui.feature.presentation.incomes.screen.IncomesScreen
import com.example.myfinance.ui.feature.presentation.settings.screen.SettingsScreen
import com.example.myfinance.ui.feature.presentation.transactions_history.screen.TransactionsHistoryScreen
import com.example.myfinance.ui.navigation.NavRoutes.ChangeTransaction

@Composable
fun FinappNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = NavRoutes.Expenses, modifier = modifier) {

        navigation<NavRoutes.Expenses>(startDestination = NavRoutes.Transactions) {
            composable<NavRoutes.Transactions> { backStackEntry ->
                ExpensesScreen(

                    onHistoryClicked = {
                        navController.navigate(route = NavRoutes.TransactionsHistory)
                    },

                    onFabClicked = {
                        navController.navigate(ChangeTransaction(null))
                    },

                    onItemClicked = { transactionId ->
                        navController.navigate(ChangeTransaction(transactionId))
                    }
                )
            }

            composable<ChangeTransaction> { backStackEntry ->
                val args = backStackEntry.toRoute<ChangeTransaction>()
                ChangeTransactionScreen(
                    isIncome = false,
                    transactionId = args.transactionId,
                    returnToPreviousScreen = { navController.popBackStack() }
                )
            }

            composable<NavRoutes.TransactionsHistory> { backStackEntry ->
                TransactionsHistoryScreen(
                    isIncome = false,
                    onBackArrowClicked = { navController.popBackStack() },
                    onAnalysisClicked = { navController.navigate(route = NavRoutes.Analysis)},

                    onItemClicked = { transactionId ->
                        navController.navigate(ChangeTransaction(transactionId))
                    }
                )
            }

            composable<NavRoutes.Analysis> { backStackEntry ->
                AnalysisScreen(
                    isIncome = false,
                    onBackArrowClicked = { navController.popBackStack() }
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
                        navController.navigate(ChangeTransaction(null))
                    },

                    onItemClicked = { transactionId ->
                        navController.navigate(ChangeTransaction(transactionId))
                    }
                )
            }

            composable<ChangeTransaction> { backStackEntry ->
                val args = backStackEntry.toRoute<ChangeTransaction>()
                ChangeTransactionScreen(
                    isIncome = true,
                    transactionId = args.transactionId,
                    returnToPreviousScreen = { navController.popBackStack() }
                )
            }

            composable<NavRoutes.TransactionsHistory> { backStackEntry ->
                TransactionsHistoryScreen(
                    isIncome = true,
                    onBackArrowClicked = { navController.popBackStack() },
                    onAnalysisClicked = { navController.navigate(route = NavRoutes.Analysis)},

                    onItemClicked = { transactionId ->
                        navController.navigate(ChangeTransaction(transactionId))
                    }
                )
            }

            composable<NavRoutes.Analysis> { backStackEntry ->
                AnalysisScreen(
                    isIncome = true,
                    onBackArrowClicked = { navController.popBackStack() }
                )
            }
        }

        navigation<NavRoutes.Account>(startDestination = NavRoutes.AccountStatistic) {
            composable<NavRoutes.AccountStatistic> {
                AccountScreen(
                    onEditAccountClicked = { navController.navigate(route = NavRoutes.EditAccount) }
                )
            }

            composable<NavRoutes.EditAccount> {
                EditAccountScreen(
                    returnToAccountScreen = { navController.popBackStack() }
                )
            }
        }


        composable<NavRoutes.Categories> { CategoryScreen() }

        composable<NavRoutes.Settings> { SettingsScreen(settings) }

    }
}
