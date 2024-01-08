package fr.iut.alldev.allin.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor

@Immutable
data class AllInColors(
    val allInDark: Color,
    val allInDarkGrey300: Color,
    val allInDarkGrey200: Color,
    val allInDarkGrey100: Color,
    val allInDarkGrey50: Color,
    val allInGrey: Color,
    val allInLightGrey300: Color,
    val allInLightGrey200: Color,
    val allInLightGrey100: Color,
    val allInLightGrey50: Color,
    val allInWhite: Color,
    val white: Color,
    val black: Color,
    val allInPink: Color,
    val allInMint: Color,
    val allInPurple: Color,
    val allInLoginPurple: Color,
    val allInBlue: Color,
    val allInDarkBlue: Color,
    val allInBarPurple: Color,
    val allInBarPink: Color,
    val allInBetFinish: Color,
    val allInBetInProgress: Color,
    val allInBetWaiting: Color,
    val allInBetFinishText: Color,
    val allInBetInProgressText: Color,
    val allInBetWaitingText: Color,
    val allInMainGradient: Brush,
    val allInBar1stGradient: Brush,
    val allInBar2ndGradient: Brush,
    val allInTextGradient: Brush,
    val allInLoginGradient: Brush,
    val allInDarkGradient: Brush,
)

internal val LocalColors = staticCompositionLocalOf {
    AllInColors(
        allInDark = Color.Unspecified,
        allInDarkGrey300 = Color.Unspecified,
        allInDarkGrey200 = Color.Unspecified,
        allInDarkGrey100 = Color.Unspecified,
        allInDarkGrey50 = Color.Unspecified,
        allInGrey = Color.Unspecified,
        allInLightGrey300 = Color.Unspecified,
        allInLightGrey200 = Color.Unspecified,
        allInLightGrey100 = Color.Unspecified,
        allInLightGrey50 = Color.Unspecified,
        allInWhite = Color.Unspecified,
        white = Color.Unspecified,
        black = Color.Unspecified,
        allInPink = Color.Unspecified,
        allInPurple = Color.Unspecified,
        allInLoginPurple = Color.Unspecified,
        allInBarPurple = Color.Unspecified,
        allInBarPink = Color.Unspecified,
        allInBlue = Color.Unspecified,
        allInMint = Color.Unspecified,
        allInDarkBlue = Color.Unspecified,
        allInBetFinish = Color.Unspecified,
        allInBetInProgress = Color.Unspecified,
        allInBetWaiting = Color.Unspecified,
        allInBetFinishText = Color.Unspecified,
        allInBetInProgressText = Color.Unspecified,
        allInBetWaitingText = Color.Unspecified,
        allInMainGradient = SolidColor(Color.Unspecified),
        allInBar1stGradient = SolidColor(Color.Unspecified),
        allInBar2ndGradient = SolidColor(Color.Unspecified),
        allInTextGradient = SolidColor(Color.Unspecified),
        allInLoginGradient = SolidColor(Color.Unspecified),
        allInDarkGradient = SolidColor(Color.Unspecified)
    )
}

@Immutable
data class AllInThemeColors(
    val mainSurface: Color,
    val onMainSurface: Color,
    val background: Color,
    val onBackground: Color,
    val tint1: Color,
    val background2: Color,
    val onBackground2: Color,
    val border: Color,
    val disabled: Color,
    val disabledBorder: Color,
)

internal val LocalThemeColors = staticCompositionLocalOf {
    AllInThemeColors(
        mainSurface = Color.Unspecified,
        onMainSurface = Color.Unspecified,
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        tint1 = Color.Unspecified,
        background2 = Color.Unspecified,
        onBackground2 = Color.Unspecified,
        border = Color.Unspecified,
        disabled = Color.Unspecified,
        disabledBorder = Color.Unspecified
    )
}