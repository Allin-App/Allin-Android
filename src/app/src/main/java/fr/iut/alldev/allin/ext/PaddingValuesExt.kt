package fr.iut.alldev.allin.ext

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
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
fun WindowInsets.asPaddingValues(top: Dp = 0.dp, bottom: Dp = 0.dp, start: Dp = 0.dp, end: Dp = 0.dp): PaddingValues
    = this.asPaddingValues() + PaddingValues(start, top, end, bottom)

@ReadOnlyComposable
@Composable
fun WindowInsets.asPaddingValues(horizontal: Dp = 0.dp, vertical: Dp = 0.dp): PaddingValues
        = this.asPaddingValues() + PaddingValues(horizontal, vertical)

@ReadOnlyComposable
@Composable
fun WindowInsets.asPaddingValues(all: Dp = 0.dp): PaddingValues
        = this.asPaddingValues() + PaddingValues(all)