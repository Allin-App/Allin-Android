package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.theme.AllInTheme
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllInCard(
    modifier: Modifier = Modifier,
    onClick: (()->Unit)? = null,
    radius: Dp = 15.dp,
    enabled: Boolean = true,
    backgroundColor: Color = AllInTheme.themeColors.background,
    disabledBackgroundColor: Color = AllInTheme.themeColors.disabled,
    backgroundBrush: Brush? = null,
    borderWidth: Dp? = null,
    borderColor: Color = AllInTheme.themeColors.border,
    disabledBorderColor: Color = AllInTheme.themeColors.disabled_border,
    borderBrush: Brush? = null,
    content: @Composable ()->Unit
) {

    val cardShape = AbsoluteSmoothCornerShape(radius, smoothnessAsPercent = 100)
    val cardModifier = modifier.fillMaxWidth()
        .run {
        backgroundBrush?.let{
            this.clip(cardShape).background(it)
        } ?: this
    }
    val cardBorders = borderWidth?.let{
            width -> borderBrush?.let{BorderStroke(width, it)}
        ?: BorderStroke(width, if(enabled) borderColor else disabledBorderColor)
    }
    val cardColors = CardDefaults.cardColors(
        containerColor = if(backgroundBrush!=null) Color.Transparent else backgroundColor,
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
