package fr.iut.alldev.allin.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
data class AllInColors(
    val allIn_Dark: Color,
    val allIn_DarkGrey100: Color,
    val allIn_DarkGrey50: Color,
    val allIn_Grey: Color,
    val allIn_LightGrey300: Color,
    val allIn_LightGrey200: Color,
    val allIn_LightGrey100: Color,
    val allIn_LightGrey50: Color,
    val allIn_White: Color,
    val white: Color,
    val allIn_Pink: Color,
    val allIn_Purple: Color,
    val allIn_Blue: Color,
    val allIn_MainGradient: Brush,
    val allIn_TextGradient: Brush
)

internal val LocalColors = staticCompositionLocalOf {
    AllInColors(
        allIn_Dark = Color.Unspecified,
        allIn_DarkGrey100 = Color.Unspecified,
        allIn_DarkGrey50 = Color.Unspecified,
        allIn_Grey = Color.Unspecified,
        allIn_LightGrey300 = Color.Unspecified,
        allIn_LightGrey200 = Color.Unspecified,
        allIn_LightGrey100 = Color.Unspecified,
        allIn_LightGrey50 = Color.Unspecified,
        allIn_White = Color.Unspecified,
        white = Color.Unspecified,
        allIn_Pink = Color.Unspecified,
        allIn_Purple = Color.Unspecified,
        allIn_Blue = Color.Unspecified,

        allIn_MainGradient = Brush.linearGradient(
            0.0f to Color(0xFFf951a8),
            0.5f to Color(0xFFaa7ef3),
            1.0f to Color(0xFF199fee),
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        ),
        allIn_TextGradient = Brush.horizontalGradient(
            0.0f to Color(0xFFF876C1),
            1.0f to Color(0xFF2399F8)
        )
    )
}