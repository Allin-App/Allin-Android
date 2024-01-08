package fr.iut.alldev.allin.ui.core

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.theme.AllInTheme
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllInCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    radius: Dp = 15.dp,
    enabled: Boolean = true,
    backgroundColor: Color = AllInTheme.themeColors.background,
    disabledBackgroundColor: Color = AllInTheme.themeColors.disabled,
    backgroundBrush: Brush? = null,
    borderWidth: Dp? = null,
    borderColor: Color = AllInTheme.themeColors.border,
    disabledBorderColor: Color = AllInTheme.themeColors.disabledBorder,
    borderBrush: Brush? = null,
    content: @Composable () -> Unit,
) {

    val cardShape = AbsoluteSmoothCornerShape(radius, smoothnessAsPercent = 100)
    val cardModifier = modifier
        .run {
            backgroundBrush?.let {
                this
                    .clip(cardShape)
                    .background(it)
            } ?: this
        }
    val cardBorders = borderWidth?.let { width ->
        borderBrush?.let { BorderStroke(width, it) }
            ?: BorderStroke(width, if (enabled) borderColor else disabledBorderColor)
    }
    val cardColors = CardDefaults.cardColors(
        containerColor = if (backgroundBrush != null) Color.Transparent else backgroundColor,
        disabledContainerColor = disabledBackgroundColor
    )
    onClick?.let {
        Card(
            modifier = cardModifier,
            enabled = enabled,
            onClick = it,
            shape = cardShape,
            border = cardBorders,
            colors = cardColors,
        ) {
            content()
        }
    } ?: run {
        Card(
            modifier = cardModifier,
            shape = cardShape,
            border = cardBorders,
            colors = cardColors
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllInBouncyCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    radius: Dp = 15.dp,
    enabled: Boolean = true,
    backgroundColor: Color = AllInTheme.themeColors.background,
    disabledBackgroundColor: Color = AllInTheme.themeColors.disabled,
    backgroundBrush: Brush? = null,
    borderWidth: Dp? = null,
    borderColor: Color = AllInTheme.themeColors.border,
    disabledBorderColor: Color = AllInTheme.themeColors.disabledBorder,
    borderBrush: Brush? = null,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) .95f else 1f,
        animationSpec = spring(
            Spring.DampingRatioHighBouncy,
            Spring.StiffnessMediumLow
        )
    )
    AllInCard(
        modifier = modifier
            .combinedClickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onClick?.let { it() } }
            )
            .scale(scale),
        onClick = null,
        radius = radius,
        enabled = enabled,
        backgroundColor = backgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
        backgroundBrush = backgroundBrush,
        borderWidth = borderWidth,
        borderColor = borderColor,
        disabledBorderColor = disabledBorderColor,
        borderBrush = borderBrush,
        content = content
    )

}