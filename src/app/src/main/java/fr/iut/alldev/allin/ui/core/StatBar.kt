package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun StatBar(
    percentage: Float,
    modifier: Modifier = Modifier,
    leadingBrush: Brush = AllInTheme.colors.allInBar1stGradient,
    trailingBrush: Brush = AllInTheme.colors.allInBar2ndGradient,
    icon: (@Composable () -> Unit)? = null,
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth(percentage)
                    .padding(end = if (percentage == 1f) 25.dp else 0.dp)
                    .clip(
                        AbsoluteRoundedCornerShape(
                            topLeftPercent = 50,
                            bottomLeftPercent = 50,
                            topRightPercent = 0,
                            bottomRightPercent = 0
                        )
                    )
                    .background(leadingBrush)
            )
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth()
                    .padding(start = if (percentage == 0f) 25.dp else 15.dp)
                    .clip(
                        AbsoluteRoundedCornerShape(
                            topLeftPercent = 0,
                            bottomLeftPercent = 0,
                            topRightPercent = 50,
                            bottomRightPercent = 50
                        )
                    )
                    .background(trailingBrush)
            )

        }
        PercentagePositionnedElement(percentage = percentage) {
            icon?.invoke() ?: when (percentage) {
                0f -> Icon(
                    painter = painterResource(id = R.drawable.fire_solid),
                    tint = AllInTheme.colors.allInPink,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )

                1f -> Icon(
                    painter = painterResource(id = R.drawable.fire_solid),
                    tint = AllInTheme.colors.allInPurple,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )

                else -> Image(
                    painter = painterResource(id = R.drawable.bar_flame),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }

        }
    }
}

@Preview
@Composable
private fun StatBar0Preview() {
    AllInTheme {
        StatBar(percentage = 0f)
    }
}

@Preview
@Composable
private fun StatBar33Preview() {
    AllInTheme {
        StatBar(percentage = 0.33f)
    }
}

@Preview
@Composable
private fun StatBar50Preview() {
    AllInTheme {
        StatBar(percentage = 0.5f)
    }
}

@Preview
@Composable
private fun StatBar66Preview() {
    AllInTheme {
        StatBar(percentage = 0.66f)
    }
}

@Preview
@Composable
private fun StatBar100Preview() {
    AllInTheme {
        StatBar(percentage = 1f)
    }
}