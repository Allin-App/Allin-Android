package fr.iut.alldev.allin.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
data class AllInColors(
    val allIn_Dark: Color,
    val allIn_DarkerGrey: Color,
    val allIn_DarkGrey: Color,
    val allIn_Grey: Color,
    val allIn_LightGrey: Color,
    val allIn_LighterGrey: Color,
    val allIn_White: Color,
    val white: Color,
    val allIn_Pink: Color,
    val allIn_gradient: Brush
)

internal val LocalColors = staticCompositionLocalOf {
    AllInColors(
        allIn_Dark = Color(0xFF2A2A2A),
        allIn_DarkerGrey = Color(0xFF393939),
        allIn_DarkGrey = Color(0xFF454545),
        allIn_Grey = Color(0xFF525252),
        allIn_LightGrey = Color(0XFFAAAAAA),
        allIn_LighterGrey = Color(0XFFC5C5C5),
        allIn_White = Color(0xFFEBEBF6),
        white = Color(0xFFFFFFFF),
        allIn_Pink = Color(0xFFFF2A89),
        allIn_gradient = Brush.linearGradient(
            0.0f to Color(0xFFf951a8),
            0.5f to Color(0xFFaa7ef3),
            1.1f to Color(0xFF199fee),
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )
    )
}