package fr.iut.alldev.allin.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight


@Composable
fun AllInTheme(
    content: @Composable () -> Unit
)  {
    val customColors = AllInColors(
        allIn_Dark = Color(0xFF2A2A2A),
        allIn_DarkGrey300 = Color(0xFF1c1c1c),
        allIn_DarkGrey200 = Color(0xFF262626),
        allIn_DarkGrey100 = Color(0xFF393939),
        allIn_DarkGrey50 = Color(0xFF454545),
        allIn_Grey = Color(0xFF525252),
        allIn_LightGrey300 = Color(0XFFAAAAAA),
        allIn_LightGrey200 = Color(0XFFC5C5C5),
        allIn_LightGrey100 = Color(0XFFEBEBEB),
        allIn_LightGrey50 = Color(0XFFF7F7F7),
        allIn_White = Color(0xFFEBEBF6),
        white = Color(0xFFFFFFFF),
        black = Color(0xFF000000),
        allIn_Pink = Color(0xFFFF2A89),
        allIn_Purple = Color(0xFF7D79FF),
        allIn_LoginPurple = Color(0xFF7F7BFB),
        allIn_BarPurple = Color(0xFF846AC9),
        allIn_BarPink = Color(0xFFFE2B8A),
        allIn_Blue = Color(0xFF6a89fa),
        allIn_DarkBlue = Color(0xFF323078),

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

    val customTypography = AllInTypography(
        h1 = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.ExtraBold,
        ),
        h2 = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Bold,
        ),
        h3 = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
        ),
        m = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Medium,
        ),
        r = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Normal,
        ),
        s = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Light,
        ),
        xs = TextStyle(
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.ExtraLight,
        )
    )

    val customTheme = if(isSystemInDarkTheme()){
        AllInThemeColors(
            main_surface = customColors.allIn_DarkGrey300,
            on_main_surface = customColors.allIn_White,
            background = customColors.allIn_DarkGrey200,
            on_background = customColors.white,
            tint_1 = customColors.white,
            background_2 = customColors.allIn_Dark,
            on_background_2 = customColors.allIn_LightGrey200,
            border = customColors.allIn_DarkGrey100,
            disabled = customColors.allIn_DarkGrey200,
            disabled_border = customColors.allIn_DarkGrey100
        )
    }else{
        AllInThemeColors(
            main_surface = customColors.allIn_White,
            on_main_surface = customColors.allIn_Dark,
            background = customColors.white,
            on_background = customColors.allIn_DarkBlue,
            tint_1 = customColors.allIn_LoginPurple,
            background_2 = customColors.allIn_LightGrey50,
            on_background_2 = customColors.allIn_LightGrey300,
            border = customColors.allIn_LightGrey100,
            disabled = customColors.allIn_LightGrey100,
            disabled_border = customColors.allIn_LightGrey200

        )
    }

    CompositionLocalProvider(
        LocalColors provides customColors,
        LocalTypography provides customTypography,
        LocalThemeColors provides customTheme
    ){
        content()
    }
}

object AllInTheme {
    val colors: AllInColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: AllInTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val themeColors: AllInThemeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeColors.current
}


class AllInRippleTheme(
    val color: Color,
) : RippleTheme {
    @Composable
    override fun defaultColor(): Color = color

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Color.Black,
        lightTheme = !isSystemInDarkTheme()
    )
}