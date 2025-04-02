package com.example.userdirectory.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//val LocalCustomColor = staticCompositionLocalOf { LightCustomColor }
val LocalTypography = staticCompositionLocalOf { customTypography }

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightOnPrimaryContainer,
    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnSurface,
    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    secondaryContainer = LightSecondaryContainer,
    onSecondaryContainer = LightOnSecondaryContainer,
    error = LightError,
    onError = LightOnError
)

// Dark Theme Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkOnPrimaryContainer,
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    secondaryContainer = DarkSecondaryContainer,
    onSecondaryContainer = DarkOnSecondaryContainer,
    error = DarkError,
    onError = DarkOnError
)
//CustomColorImplementation
//val LightCustomColor = CustomColorsDataClass(
//    progressbarColor = lightProgressBar,
//    textColor = LightText
//)
//val DarkCustomColor = CustomColorsDataClass(
//    progressbarColor = darkProgressBar,
//    textColor = DarkText
//)

@Composable
fun UserDirectoryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    SideEffect {
        systemUiController.setStatusBarColor(
            color = colorScheme.background,
            darkIcons = !darkTheme
        )
        systemUiController.setNavigationBarColor(
            color = colorScheme.background,
            navigationBarContrastEnforced = false
        )
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = customTypo,
        content = content
    )


}

