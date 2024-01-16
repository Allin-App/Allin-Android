package fr.iut.alldev.allin.ui.bet.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ext.shadow
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInCard
import fr.iut.alldev.allin.ui.core.HighlightedText
import kotlin.math.ceil

@Composable
fun BetScreenPopularCard(
    nbPlayers: Int,
    points: Float,
    pointUnit: String,
    title: String,
    modifier: Modifier = Modifier,
) {
    AllInCard(
        modifier = modifier
            .let {
                if (isSystemInDarkTheme()) {
                    it.shadow(
                        colors = listOf(
                            AllInTheme.colors.allInPink,
                            AllInTheme.colors.allInBlue
                        ),
                        blurRadius = 10.dp,
                        alpha = .5f,
                        cornerRadius = 15.dp
                    )
                } else {
                    it.shadow(
                        color = Color.Black,
                        blurRadius = 10.dp,
                        alpha = .3f,
                        cornerRadius = 15.dp
                    )
                }
            }
            .fillMaxWidth(),
        backgroundColor = AllInTheme.colors.allInDark,
        borderWidth = 2.dp,
        borderBrush = AllInTheme.colors.allInMainGradient
    ) {
        Column(modifier = Modifier.padding(13.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.allin_fire),
                    modifier = Modifier.size(15.dp),
                    contentDescription = null,
                    tint = AllInTheme.colors.allInPink
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = stringResource(id = R.string.Popular),
                    color = AllInTheme.colors.allInPink,
                    fontSize = 17.sp,
                    style = AllInTheme.typography.h2
                )
            }
            Text(
                text = title,
                color = AllInTheme.colors.white,
                fontSize = 20.sp,
                style = AllInTheme.typography.h1,
                modifier = Modifier.padding(vertical = 22.dp)
            )
            Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                HighlightedText(
                    text = pluralStringResource(
                        id = R.plurals.n_players,
                        nbPlayers,
                        nbPlayers
                    ),
                    query = nbPlayers.toString(),
                    highlightStyle = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = AllInTheme.colors.allInPink
                    ),
                    color = AllInTheme.colors.white,
                    style = AllInTheme.typography.p1,
                    fontSize = 15.sp
                )
                Text(
                    text = " - ",
                    color = AllInTheme.colors.white,
                    style = AllInTheme.typography.p1,
                    fontSize = 15.sp
                )
                val pointsText = if (points % 1 == 0f) {
                    stringResource(id = R.string.int_and_unit, points.toInt(), pointUnit)
                } else {
                    stringResource(id = R.string.float_and_unit, points, pointUnit)
                }
                HighlightedText(
                    text = pluralStringResource(
                        id = R.plurals.n_points_at_stake,
                        if (pointUnit.isEmpty()) ceil(points).toInt() else 2,
                        pointsText
                    ),
                    query = pointsText,
                    highlightStyle = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = AllInTheme.colors.allInPink
                    ),
                    color = AllInTheme.colors.white,
                    style = AllInTheme.typography.p1,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun BetScreenPopularCardPreview() {
    AllInTheme {
        BetScreenPopularCard(
            nbPlayers = 12,
            points = 2.35f,
            pointUnit = "k",
            title = "Emre va réussir son TP de CI/CD mercredi?"
        )
    }
}

@Preview
@Composable
private fun BetScreenPopularCardSingularPreview() {
    AllInTheme {
        BetScreenPopularCard(
            nbPlayers = 1,
            points = 1.0f,
            pointUnit = "",
            title = "Emre va réussir son TP de CI/CD mercredi?"
        )
    }
}