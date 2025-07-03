package com.example.myfinance.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Green,
    secondary = LightGreen,
    tertiary = Grey,
    outlineVariant = LightGrey,
    background = White,
    surface = Light,
    error = Red,

    onPrimary = Black,
    onSecondary = Black,
    onTertiary = Black,
    onBackground = Black,
    onSurface = Black,
    onSurfaceVariant = DarkGrey,
    onError = White,
)

private val LightColorScheme = lightColorScheme(
    primary = Green,
    secondary = LightGreen,
    tertiary = Grey,
    outlineVariant = LightGrey,
    background = White,
    surface = Light,
    error = Red,

    onPrimary = Black,
    onSecondary = Black,
    onTertiary = Black,
    onBackground = Black,
    onSurface = Black,
    onSurfaceVariant = DarkGrey,
    onError = White,
)

@Composable
fun MyFinanceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (darkTheme) DarkColorScheme
    else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
