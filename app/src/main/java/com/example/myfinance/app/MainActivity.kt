package com.example.myfinance.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.di.ActivityComponent
import com.example.myfinance.di.ScreenComponent
import com.example.myfinance.ui.common.navbar.FinappNavBar
import com.example.myfinance.ui.navigation.FinappNavHost
import com.example.myfinance.ui.theme.MyFinanceTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject lateinit var screenComponentFactory: ScreenComponent.Factory
    internal lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = (application as FinApp).appComponent
        activityComponent = appComponent.activityComponentFactory().create(this)
        activityComponent.inject(this)

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
                        screenComponentFactory,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
