package com.uragiristereo.mejiboard.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = DeepPurple200,
    primaryVariant = DeepPurple700,
//    secondary = Teal200
    secondary = DeepPurple200
)

private val BlackColorPalette = darkColors(
    primary = DeepPurple200,
    primaryVariant = DeepPurple700,
//    secondary = Teal200,
    secondary = DeepPurple200,
    background = Color.Black,
    surface = Color.Black
)

private val LightColorPalette = lightColors(
    primary = DeepPurple500,
    primaryVariant = DeepPurple700,
//    secondary = Teal200
    secondary = DeepPurple500

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MejiboardTheme(
    theme: String = "system",
    blackTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors =
        when (theme) {
            "light" -> LightColorPalette
            "dark" -> if (blackTheme) BlackColorPalette else DarkColorPalette
            "system" -> {
                if (isSystemInDarkTheme())
                    if (blackTheme)
                        BlackColorPalette
                    else
                        DarkColorPalette
                else
                    LightColorPalette
            }
            else -> LightColorPalette
        }

    rememberSystemUiController().setStatusBarColor(color = colors.surface)

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}