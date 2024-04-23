package fr.iut.alldev.allin.ui.betStatus.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.ext.toPercentageString
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun SimpleStatBar(
    percentage: Float,
    response: String,
    isWin: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = response,
            color = if (isWin) {
                AllInTheme.colors.allInBarPink
            } else {
                AllInTheme.colors.allInBlue
            },
            style = AllInTheme.typography.sm2,
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .let { itModifier ->
                        if (percentage != 0f && !percentage.isNaN()) itModifier.weight(percentage)
                        else itModifier
                    }
                    .clip(
                        AbsoluteRoundedCornerShape(
                            topLeftPercent = 50,
                            bottomLeftPercent = 50,
                            topRightPercent = 0,
                            bottomRightPercent = 0
                        )
                    )
                    .background(
                        if (isWin) {
                            AllInTheme.colors.allInBar2ndGradient
                        } else {
                            AllInTheme.colors.allInBar1stGradient
                        }
                    )
            )

            Icon(
                painter = painterResource(id = R.drawable.fire_solid),
                tint = if (isWin) {
                    AllInTheme.colors.allInBarViolet
                } else {
                    AllInTheme.colors.allInBarPurple
                },
                contentDescription = null,
                modifier = Modifier.size(32.dp).offset((-7).dp)
            )
            Text(
                modifier = Modifier.let { itModifier ->
                    if (percentage != 1f && !percentage.isNaN()) itModifier.weight(1 - percentage)
                    else itModifier
                },
                text = percentage.toPercentageString(),
                style = AllInTheme.typography.h1.copy(
                    fontSize = if (isWin) 24.sp else 16.sp
                ),
                color = if (isWin) {
                    AllInTheme.colors.allInBarViolet
                } else {
                    AllInTheme.colors.allInBarPurple
                }
            )
        }
    }
}

@Preview
@Composable
private fun SimpleStatBarBarWinPreview() {
    AllInTheme {
        SimpleStatBar(
            percentage = .8f,
            response = "Answer",
            isWin = true
        )
    }
}

@Preview
@Composable
private fun SimpleStatBarBarPreview() {
    AllInTheme {
        SimpleStatBar(
            percentage = .4f,
            response = "Answer",
            isWin = false
        )
    }
}