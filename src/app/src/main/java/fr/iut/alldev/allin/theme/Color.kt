package fr.iut.alldev.allin.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
data class AllInColors(
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

internal val LocalColors = staticCompositionLocalOf {
    AllInColors(
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

internal object AllInColorToken {
    val black = Color(0xFF000000)
    val allInDark = Color(0xFF2A2A2A)
    val allInDarkGrey300 = Color(0xFF1c1c1c)
    val allInDarkGrey200 = Color(0xFF262626)
    val allInDarkGrey100 = Color(0xFF393939)
    val allInDarkGrey50 = Color(0xFF454545)
    val allInGrey = Color(0xFF525252)
    val allInLightGrey300 = Color(0XFFAAAAAA)
    val allInLightGrey200 = Color(0XFFC5C5C5)
    val allInLightGrey100 = Color(0XFFEBEBEB)
    val allInLightGrey50 = Color(0XFFF7F7F7)
    val allInWhite = Color(0xFFEBEBF6)
    val white = Color(0xFFFFFFFF)

    val allInDarkBlue = Color(0xFF323078)
    val allInBlue = Color(0xFF6a89fa)
    val allInPurple = Color(0xFF7D79FF)
    val allInLoginPurple = Color(0xFF7F7BFB)
    val allInBarPurple = Color(0xFF846AC9)
    val allInPink = Color(0xFFFF2A89)
    val allInBarPink = Color(0xFFFE2B8A)
    val allInBarViolet = Color(0xFFC249A8)
    val allInMint = Color(0xFFC4DEE9)

    val allInBetFinish = Color(0xFF353535)
    val allInBetInProgress = Color(0xFF604BDB)
    val allInBetWaiting = Color(0xFFDF3B9A)

    val allInBetFinishText = Color(0xFFA7A7A7)
    val allInBetInProgressText = Color(0xFF4636A3)
    val allInBetWaitingText = Color(0xFF852E6C)

    val allInMainGradient = Brush.linearGradient(
        0.0f to Color(0xFFf951a8),
        0.5f to Color(0xFFaa7ef3),
        1.0f to Color(0xFF199fee),
        start = Offset(0f, Float.POSITIVE_INFINITY),
        end = Offset(Float.POSITIVE_INFINITY, 0f)
    )
    val allInMainGradientReverse = Brush.linearGradient(
        0.0f to Color(0xFF199fee),
        0.5f to Color(0xFFaa7ef3),
        1.0f to Color(0xFFf951a8),
        start = Offset(0f, Float.POSITIVE_INFINITY),
        end = Offset(Float.POSITIVE_INFINITY, 0f)
    )
    val allInBar1stGradient = Brush.horizontalGradient(
        0.0f to Color(0xFF2599F8),
        1.0f to Color(0xFF846AC9)
    )
    val allInBar2ndGradient = Brush.horizontalGradient(
        0.0f to Color(0xFFFE2B8A),
        1.0f to Color(0xFFC249A8)
    )
    val allInTextGradient = Brush.horizontalGradient(
        0.0f to Color(0xFFF876C1),
        1.0f to Color(0xFF2399F8)
    )
    val allInLoginGradient = Brush.linearGradient(
        0.0f to Color(0xFFEC1794),
        0.5f to Color(0xFFaa7ef3),
        1.0f to Color(0xFF00EEEE),
        start = Offset(0f, Float.POSITIVE_INFINITY),
        end = Offset(Float.POSITIVE_INFINITY, 0f)
    )
    val allInDarkGradient = Brush.horizontalGradient(
        0.0f to Color(0xFF595959),
        1.0f to Color(0xFF000000)
    )
}