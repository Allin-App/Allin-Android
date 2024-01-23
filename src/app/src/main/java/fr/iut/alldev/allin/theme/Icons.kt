package fr.iut.alldev.allin.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter

@Immutable
data class AllInIcons(
    val allCoins: @Composable () -> Painter,
)

internal val LocalIcons = staticCompositionLocalOf {
    AllInIcons(
        allCoins = { ColorPainter(Color.Unspecified) }
    )
}