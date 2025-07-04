package com.example.myfinance.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.ui.common.navbar.FinappNavBar
import com.example.myfinance.ui.navigation.FinappNavHost
import com.example.myfinance.ui.theme.MyFinanceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyFinanceTheme {
                val navController = rememberNavController()
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentWindowInsets = WindowInsets(0.dp),
                    bottomBar = { FinappNavBar(navController) }
                ) { innerPadding ->
                    FinappNavHost(
                        navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
