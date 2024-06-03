package fr.iut.alldev.allin.ext

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.safeContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@ReadOnlyComposable
@Composable
operator fun PaddingValues.plus(paddingValues: PaddingValues): PaddingValues {
    val direction = LocalLayoutDirection.current
    return PaddingValues(
        top = this.calculateTopPadding() + paddingValues.calculateTopPadding(),
        bottom = this.calculateBottomPadding() + paddingValues.calculateBottomPadding(),
        start = this.calculateStartPadding(direction) + paddingValues.calculateStartPadding(direction),
        end = this.calculateEndPadding(direction) + paddingValues.calculateEndPadding(direction),
    )
}

@ReadOnlyComposable
@Composable
fun WindowInsets.asPaddingValues(top: Dp = 0.dp, bottom: Dp = 0.dp, start: Dp = 0.dp, end: Dp = 0.dp): PaddingValues =
    this.asPaddingValues() + PaddingValues(start, top, end, bottom)

@ReadOnlyComposable
@Composable
fun WindowInsets.asPaddingValues(horizontal: Dp = 0.dp, vertical: Dp = 0.dp): PaddingValues =
    this.asPaddingValues() + PaddingValues(horizontal, vertical)

@ReadOnlyComposable
@Composable
fun WindowInsets.asPaddingValues(all: Dp = 0.dp): PaddingValues = this.asPaddingValues() + PaddingValues(all)

@ReadOnlyComposable
@Composable
fun WindowInsets.takeBottomOnly(): WindowInsets {
    val density = LocalDensity.current
    return WindowInsets(bottom = this.getBottom(density))
}

@ReadOnlyComposable
@Composable
fun WindowInsets.takeTopOnly(): WindowInsets {
    val density = LocalDensity.current
    return WindowInsets(top = this.getTop(density))
}

@Composable
fun bottomSheetNavigationBarsInsets(): WindowInsets {
    val density = LocalDensity.current
    val navBar = WindowInsets.navigationBars
    if (navBar.getBottom(density) == 0) {
        val safeContent = WindowInsets.safeContent
        if (navBar.getBottom(density) == 0) {
            return WindowInsets(bottom = 40.dp)
        }
        return safeContent.takeBottomOnly()
    }
    return navBar
}

@Composable
fun bottomSheetNavigationBarsPadding(): PaddingValues = bottomSheetNavigationBarsInsets().asPaddingValues()