package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun StatBar(
    percentage: Float,
) {
    val radius100percent = if (percentage == 1f) 50 else 0
    val radius0percent = if (percentage == 0f) 50 else 0
    Box {
        Row(
            Modifier.align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth(percentage)
                    .clip(
                        AbsoluteRoundedCornerShape(
                            topLeftPercent = 50,
                            bottomLeftPercent = 50,
                            topRightPercent = radius100percent,
                            bottomRightPercent = radius100percent
                        )
                    )
                    .background(AllInTheme.colors.allInBar1stGradient)

            )
            if (percentage != 0f && percentage != 1f) {
                Spacer(modifier = Modifier.width(15.dp))
            }
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth()
                    .clip(
                        AbsoluteRoundedCornerShape(
                            topLeftPercent = radius0percent,
                            bottomLeftPercent = radius0percent,
                            topRightPercent = 50,
                            bottomRightPercent = 50
                        )
                    )
                    .background(AllInTheme.colors.allInBar2ndGradient)
            )
        }
        PercentagePositionnedElement(percentage = percentage) {
            when (percentage) {
                0f -> Icon(
                    painter = painterResource(id = R.drawable.fire_solid),
                    tint = AllInTheme.colors.allInBarPink,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )

                1f -> Icon(
                    painter = painterResource(id = R.drawable.fire_solid),
                    tint = AllInTheme.colors.allInBarPurple,
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