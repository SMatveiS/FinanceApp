package com.example.myfinance.ui.common

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
import com.example.myfinance.ui.navigation.getNavBarItems

@Composable
fun FinappNavBar(navController: NavHostController) {
    val navBarItems = remember { getNavBarItems() }

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
                selected = currentDestination?.hierarchy?.any {
                    it.route == navItem.route || navItem.route == source
                } == true,
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
