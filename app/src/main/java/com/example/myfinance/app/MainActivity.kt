package com.example.myfinance.app

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.domain.usecase.theme.DarkThemeUseCase
import com.example.myfinance.di.ActivityComponent
import com.example.myfinance.ui.navigation.FinappNavHost
import com.example.myfinance.ui.navigation.navbar.FinappNavBar
import com.example.ui.theme.MyFinanceTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    internal lateinit var activityComponent: ActivityComponent

    @Inject
    lateinit var darkThemeUseCase: DarkThemeUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = (application as FinApp).appComponent
        activityComponent = appComponent.activityComponentFactory().create(this)
        activityComponent.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val darkTheme by darkThemeUseCase.darkThemeFlow.collectAsState(false)
            MyFinanceTheme(darkTheme = darkTheme) {

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

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}

