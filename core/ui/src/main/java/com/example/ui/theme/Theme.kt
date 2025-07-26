package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val GreenDarkColorScheme = darkColorScheme(
    primary = DarkGreen,
    secondary = VeryDarkGreen,
    tertiary = VeryDarkGrey,
    outlineVariant = Grey,
    background = Black,
    surface = VeryDarkGrey,
    error = LightRed,

    onPrimary = Light,
    onSecondary = Light,
    onTertiary = Light,
    onBackground = Light,
    onSurface = Light,
    onSurfaceVariant = Light,
    onError = DarkRed,
)

private val GreenLightColorScheme = lightColorScheme(
    primary = Green,
    secondary = LightGreen,
    tertiary = Light,
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




private val BlueDarkColorScheme = darkColorScheme(
    primary = SeaColor,
    secondary = DarkBlue,
    tertiary = VeryDarkGrey,
    outlineVariant = Grey,
    background = Black,
    surface = VeryDarkGrey,
    error = LightRed,

    onPrimary = Light,
    onSecondary = Light,
    onTertiary = Light,
    onBackground = Light,
    onSurface = Light,
    onSurfaceVariant = Light,
    onError = DarkRed,
)

private val BlueLightColorScheme = lightColorScheme(
    primary = Blue,
    secondary = LightBlue,
    tertiary = Light,
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




private val PurpleDarkColorScheme = darkColorScheme(
    primary = Purple,
    secondary = DarkPurple,
    tertiary = VeryDarkGrey,
    outlineVariant = Grey,
    background = Black,
    surface = VeryDarkGrey,
    error = LightRed,

    onPrimary = Light,
    onSecondary = Light,
    onTertiary = Light,
    onBackground = Light,
    onSurface = Light,
    onSurfaceVariant = Light,
    onError = DarkRed,
)

private val PurpleLightColorScheme = lightColorScheme(
    primary = LightPurple,
    secondary = LightPink,
    tertiary = Light,
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
    mainColor: Int = 0,
    content: @Composable () -> Unit
) {
    // Основной цвет - зелёный
    val colorScheme = if (mainColor == 0) {
        if (darkTheme) GreenDarkColorScheme
        else GreenLightColorScheme
    // Основной цвет - синий
    } else if (mainColor == 1) {
        if (darkTheme) BlueDarkColorScheme
        else BlueLightColorScheme
    // Основной цвет - фиолетовый
    } else {
        if (darkTheme) PurpleDarkColorScheme
        else PurpleLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
