package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF25945E),
    secondary = Color(0xFF194222),
    tertiary = Color(0xFF3B383E),
    outlineVariant = Color(0xFF938F99),
    background = Black,
    surface = Color(0xFF3B383E),
    error = Color(0xFFFFB4AB),

    onPrimary = Color(0xFFE7E0E8),
    onSecondary = Color(0xFFE7E0E8),
    onTertiary = Color(0xFFE7E0E8),
    onBackground = Color(0xFFE7E0E8),
    onSurface = Color(0xFFE7E0E8),
    onSurfaceVariant = Color(0xFFE7E0E8),
    onError = Color(0xFF690005),
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
