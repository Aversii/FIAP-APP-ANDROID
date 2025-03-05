package com.example.greenimpact.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Green80,  // Verde escuro elegante
    secondary = White80,  // Verde-azulado para contraste
    tertiary = White80    // Verde lima vibrante
)

private val LightColorScheme = lightColorScheme(
    primary = Green80,   // Verde mais suave para o tema claro
    secondary = Green40,  // Verde-azulado claro para equilíbrio
    tertiary = Dark80    // Verde lima mais claro e vibranteaa
)


@Composable
fun GreenImpactTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
