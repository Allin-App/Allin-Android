package fr.iut.alldev.allin.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import fr.iut.alldev.allin.R


@Composable
fun AllInTheme(
    content: @Composable () -> Unit,
) {
    val customColors = AllInColors(
        allInDark = Color(0xFF2A2A2A),
        allInDarkGrey300 = Color(0xFF1c1c1c),
        allInDarkGrey200 = Color(0xFF262626),
        allInDarkGrey100 = Color(0xFF393939),
        allInDarkGrey50 = Color(0xFF454545),
        allInGrey = Color(0xFF525252),
        allInLightGrey300 = Color(0XFFAAAAAA),
        allInLightGrey200 = Color(0XFFC5C5C5),
        allInLightGrey100 = Color(0XFFEBEBEB),
        allInLightGrey50 = Color(0XFFF7F7F7),
        allInWhite = Color(0xFFEBEBF6),
        white = Color(0xFFFFFFFF),
        black = Color(0xFF000000),
        allInPink = Color(0xFFFF2A89),
        allInPurple = Color(0xFF7D79FF),
        allInLoginPurple = Color(0xFF7F7BFB),
        allInBarPurple = Color(0xFF846AC9),
        allInBarPink = Color(0xFFFE2B8A),
        allInBlue = Color(0xFF6a89fa),
        allInMint = Color(0xFFC4DEE9),
        allInDarkBlue = Color(0xFF323078),
        allInBetFinish = Color(0xFF353535),
        allInBetInProgress = Color(0xFF604BDB),
        allInBetWaiting = Color(0xFFDF3B9A),
        allInBetFinishText = Color(0xFFA7A7A7),
        allInBetInProgressText = Color(0xFF4636A3),
        allInBetWaitingText = Color(0xFF852E6C),

        allInMainGradient = Brush.linearGradient(
            0.0f to Color(0xFFf951a8),
            0.5f to Color(0xFFaa7ef3),
            1.0f to Color(0xFF199fee),
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        ),
        allInMainGradientReverse = Brush.linearGradient(
            0.0f to Color(0xFF199fee),
            0.5f to Color(0xFFaa7ef3),
            1.0f to Color(0xFFf951a8),
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        ),
        allInBar1stGradient = Brush.horizontalGradient(
            0.0f to Color(0xFF2599F8),
            1.0f to Color(0xFF846AC9)
        ),
        allInBar2ndGradient = Brush.horizontalGradient(
            0.0f to Color(0xFFFE2B8A),
            1.0f to Color(0xFFC249A8)
        ),
        allInTextGradient = Brush.horizontalGradient(
            0.0f to Color(0xFFF876C1),
            1.0f to Color(0xFF2399F8)
        ),
        allInLoginGradient = Brush.linearGradient(
            0.0f to Color(0xFFEC1794),
            0.5f to Color(0xFFaa7ef3),
            1.0f to Color(0xFF00EEEE),
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        ),
        allInDarkGradient = Brush.horizontalGradient(
            0.0f to Color(0xFF595959),
            1.0f to Color(0xFF000000)
        )
    )

    val customTypography = AllInTypography(
        h1 = TextStyle(
            fontFamily = fontFamilyPlusJakartaSans,
            fontWeight = FontWeight.ExtraBold
        ),
        h2 = TextStyle(
            fontFamily = fontFamilyPlusJakartaSans,
            fontWeight = FontWeight.Bold
        ),
        sm1 = TextStyle(
            fontFamily = fontFamilyPlusJakartaSans,
            fontWeight = FontWeight.SemiBold
        ),
        sm2 = TextStyle(
            fontFamily = fontFamilyPlusJakartaSans,
            fontWeight = FontWeight.Medium
        ),
        p1 = TextStyle(
            fontFamily = fontFamilyPlusJakartaSans,
            fontWeight = FontWeight.Normal
        ),
        p2 = TextStyle(
            fontFamily = fontFamilyPlusJakartaSans,
            fontWeight = FontWeight.Light
        ),
        l1 = TextStyle(
            fontFamily = fontFamilyPlusJakartaSans,
            fontWeight = FontWeight.ExtraLight
        )
    )

    val customTheme = if (isSystemInDarkTheme()) {
        AllInThemeColors(
            mainSurface = customColors.allInDarkGrey300,
            onMainSurface = customColors.allInWhite,
            background = customColors.allInDarkGrey200,
            onBackground = customColors.white,
            tint1 = customColors.white,
            background2 = customColors.allInDark,
            onBackground2 = customColors.allInLightGrey200,
            border = customColors.allInDarkGrey100,
            disabled = customColors.allInDarkGrey200,
            disabledBorder = customColors.allInDarkGrey100
        )
    } else {
        AllInThemeColors(
            mainSurface = customColors.allInWhite,
            onMainSurface = customColors.allInDark,
            background = customColors.white,
            onBackground = customColors.allInDarkBlue,
            tint1 = customColors.allInLoginPurple,
            background2 = customColors.allInLightGrey50,
            onBackground2 = customColors.allInLightGrey300,
            border = customColors.allInLightGrey100,
            disabled = customColors.allInLightGrey100,
            disabledBorder = customColors.allInLightGrey200

        )
    }

    val customIcons = AllInIcons(
        allCoins = { painterResource(id = R.drawable.allcoin) },
        logo = { painterResource(id = R.drawable.allin) }
    )

    CompositionLocalProvider(
        LocalColors provides customColors,
        LocalIcons provides customIcons,
        LocalTypography provides customTypography,
        LocalThemeColors provides customTheme
    ) {
        content()
    }
}

object AllInTheme {
    val colors: AllInColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val icons: AllInIcons
        @Composable
        @ReadOnlyComposable
        get() = LocalIcons.current

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