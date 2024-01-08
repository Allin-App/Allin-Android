package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import fr.iut.alldev.allin.theme.AllInTheme
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllInTooltip(
    text: String,
    state: TooltipState = rememberTooltipState(),
    content: @Composable () -> Unit,
) {

    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            AllInPlainTooltip {
                Text(
                    text = text,
                    color = AllInTheme.colors.allInLightGrey200,
                    style = AllInTheme.typography.r,
                    fontSize = 12.sp
                )
            }
        },
        state = state
    ) {
        content()
    }
}

internal val TooltipMinHeight = 24.dp
internal val TooltipMinWidth = 40.dp
private val AllInPlainTooltipMaxWidth = 188.dp
private val AllInPlainTooltipTopPadding = 3.dp
private val AllInPlainTooltipBottomPadding = 11.dp
private val AllInPlainTooltipHorizontalPadding = 8.dp
private val AllInPlainTooltipArrowSize = 9.dp
private val AllInPlainTooltipContentPadding =
    PaddingValues(
        top = AllInPlainTooltipTopPadding,
        bottom = AllInPlainTooltipBottomPadding,
        start = AllInPlainTooltipHorizontalPadding,
        end = AllInPlainTooltipHorizontalPadding
    )

@Composable
fun AllInPlainTooltip(
    modifier: Modifier = Modifier,
    containerColor: Color = AllInTheme.colors.allInDark,
    borderWidth: Dp = 1.dp,
    borderColor: Color = AllInTheme.colors.allInDarkGrey100,
    shape: Shape = AbsoluteSmoothCornerShape(10.dp, 100),
    content: @Composable () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = shape,
            border = BorderStroke(borderWidth, borderColor),
            color = containerColor
        ) {
            Box(
                modifier = modifier
                    .sizeIn(
                        minWidth = TooltipMinWidth,
                        maxWidth = AllInPlainTooltipMaxWidth,
                        minHeight = TooltipMinHeight
                    )
                    .padding(AllInPlainTooltipContentPadding)
            ) {
                content()
            }
        }
        Surface(
            shape = AllInArrowShape(),
            border = BorderStroke(borderWidth, borderColor),
            color = containerColor,
            modifier = Modifier
                .size(AllInPlainTooltipArrowSize)
                .offset(y = -borderWidth)
        ) {

        }
    }
}

class AllInArrowShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ) =
        Outline.Generic(
            Path().apply {
                lineTo(size.width / 2, size.height)
                lineTo(size.width, 0f)
            }
        )
}

@Preview
@Composable
private fun AllInTooltipPreview() {
    AllInTheme {
        AllInPlainTooltip(content = {
            Text(
                text = "Généralement une question qui sera répondu par les utilisateurs.",
                color = AllInTheme.colors.allInLightGrey200,
                style = AllInTheme.typography.r,
                fontSize = 10.sp
            )
        })
    }
}