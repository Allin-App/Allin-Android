package fr.iut.alldev.allin.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import fr.iut.alldev.allin.R


@Composable
fun AllInTheme(
    content: @Composable () -> Unit,
) {
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
        AllInColors(
            mainSurface = AllInColorToken.allInDarkGrey300,
            onMainSurface = AllInColorToken.allInWhite,
            background = AllInColorToken.allInDarkGrey200,
            onBackground = AllInColorToken.white,
            tint1 = AllInColorToken.white,
            background2 = AllInColorToken.allInDark,
            onBackground2 = AllInColorToken.allInLightGrey200,
            border = AllInColorToken.allInDarkGrey100,
            disabled = AllInColorToken.allInDarkGrey200,
            disabledBorder = AllInColorToken.allInDarkGrey100
        )
    } else {
        AllInColors(
            mainSurface = AllInColorToken.allInWhite,
            onMainSurface = AllInColorToken.allInDark,
            background = AllInColorToken.white,
            onBackground = AllInColorToken.allInDarkBlue,
            tint1 = AllInColorToken.allInLoginPurple,
            background2 = AllInColorToken.allInLightGrey50,
            onBackground2 = AllInColorToken.allInLightGrey300,
            border = AllInColorToken.allInLightGrey100,
            disabled = AllInColorToken.allInLightGrey100,
            disabledBorder = AllInColorToken.allInLightGrey200

        )
    }

    val customIcons = AllInIcons(
        allCoins = { painterResource(id = R.drawable.allcoin) },
        logo = { painterResource(id = R.drawable.allin) }
    )

    CompositionLocalProvider(
        LocalIcons provides customIcons,
        LocalTypography provides customTypography,
        LocalColors provides customTheme
    ) {
        content()
    }
}

object AllInTheme {
    val icons: AllInIcons
        @Composable
        @ReadOnlyComposable
        get() = LocalIcons.current

    val typography: AllInTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val colors: AllInColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
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