package fr.iut.alldev.allin.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
data class AllInColors(
    val allIn_Dark: Color,
    val allIn_DarkGrey300: Color,
    val allIn_DarkGrey200: Color,
    val allIn_DarkGrey100: Color,
    val allIn_DarkGrey50: Color,
    val allIn_Grey: Color,
    val allIn_LightGrey300: Color,
    val allIn_LightGrey200: Color,
    val allIn_LightGrey100: Color,
    val allIn_LightGrey50: Color,
    val allIn_White: Color,
    val white: Color,
    val black: Color,
    val allIn_Pink: Color,
    val allIn_Mint: Color,
    val allIn_Purple: Color,
    val allIn_LoginPurple: Color,
    val allIn_Blue: Color,
    val allIn_DarkBlue: Color,
    val allIn_BarPurple: Color,
    val allIn_BarPink: Color,
    val allIn_MainGradient: Brush,
    val allIn_Bar1stGradient: Brush,
    val allIn_Bar2ndGradient: Brush,
    val allIn_TextGradient: Brush,
    val allIn_LoginGradient: Brush
)

internal val LocalColors = staticCompositionLocalOf {
    AllInColors(
        allIn_Dark = Color.Unspecified,
        allIn_DarkGrey300 = Color.Unspecified,
        allIn_DarkGrey200 = Color.Unspecified,
        allIn_DarkGrey100 = Color.Unspecified,
        allIn_DarkGrey50 = Color.Unspecified,
        allIn_Grey = Color.Unspecified,
        allIn_LightGrey300 = Color.Unspecified,
        allIn_LightGrey200 = Color.Unspecified,
        allIn_LightGrey100 = Color.Unspecified,
        allIn_LightGrey50 = Color.Unspecified,
        allIn_White = Color.Unspecified,
        white = Color.Unspecified,
        black = Color.Unspecified,
        allIn_Pink = Color.Unspecified,
        allIn_Purple = Color.Unspecified,
        allIn_LoginPurple = Color.Unspecified,
        allIn_BarPurple = Color.Unspecified,
        allIn_BarPink = Color.Unspecified,
        allIn_Blue = Color.Unspecified,
        allIn_Mint = Color.Unspecified,
        allIn_DarkBlue = Color.Unspecified,

        allIn_MainGradient = Brush.linearGradient(
            0.0f to Color(0xFFf951a8),
            0.5f to Color(0xFFaa7ef3),
            1.0f to Color(0xFF199fee),
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        ),
        allIn_Bar1stGradient = Brush.horizontalGradient(
            0.0f to Color(0xFF2599F8),
            1.0f to Color(0xFF846AC9)
        ),
        allIn_Bar2ndGradient = Brush.horizontalGradient(
            0.0f to Color(0xFFFE2B8A),
            1.0f to Color(0xFFC249A8)
        ),
        allIn_TextGradient = Brush.horizontalGradient(
            0.0f to Color(0xFFF876C1),
            1.0f to Color(0xFF2399F8)
        ),
        allIn_LoginGradient = Brush.linearGradient(
            0.0f to Color(0xFFEC1794),
            0.5f to Color(0xFFaa7ef3),
            1.0f to Color(0xFF00EEEE),
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )
    )
}
@Immutable
data class AllInThemeColors(
    val main_surface: Color,
    val on_main_surface: Color,
    val background: Color,
    val on_background: Color,
    val tint_1: Color,
    val background_2: Color,
    val on_background_2: Color,
    val border: Color,
    val disabled: Color,
    val disabled_border: Color
)

internal val LocalThemeColors = staticCompositionLocalOf {
    AllInThemeColors(
        main_surface = Color.Unspecified,
        on_main_surface = Color.Unspecified,
        background = Color.Unspecified,
        on_background = Color.Unspecified,
        tint_1 = Color.Unspecified,
        background_2 = Color.Unspecified,
        on_background_2 = Color.Unspecified,
        border = Color.Unspecified,
        disabled = Color.Unspecified,
        disabled_border = Color.Unspecified
    )
}