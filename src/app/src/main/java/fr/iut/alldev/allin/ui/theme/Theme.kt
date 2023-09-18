package fr.iut.alldev.allin.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun AllInTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
    LocalColors provides LocalColors.current,
        content = content
    )
}

object AllInTheme {
    val colors: AllInColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}