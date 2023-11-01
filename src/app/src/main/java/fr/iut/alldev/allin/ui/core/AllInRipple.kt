package fr.iut.alldev.allin.ui.core

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import fr.iut.alldev.allin.theme.AllInRippleTheme

@Composable
fun AllInRipple(
    color: Color,
    content: @Composable ()->Unit
) {
    CompositionLocalProvider(
        LocalRippleTheme provides AllInRippleTheme(color),
        content = content
    )
}