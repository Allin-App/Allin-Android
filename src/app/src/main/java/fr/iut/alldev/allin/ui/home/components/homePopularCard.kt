package fr.iut.alldev.allin.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.core.AllInCard
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun HomePopularCards(
    nbPlayers: Int,
    points: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Box(modifier.padding(3.dp)) {
        AllInCard(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 2.dp,
                    shape = AbsoluteRoundedCornerShape(15)
                )
                .padding(2.dp),
            backgroundColor = AllInTheme.colors.allIn_Dark,
            borderWidth = 2.dp,
            borderBrush = AllInTheme.colors.allIn_MainGradient
        ) {
            Column(modifier = Modifier.padding(13.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.allin_fire),
                        modifier = Modifier.size(20.dp),
                        contentDescription = null,
                        tint = AllInTheme.colors.allIn_Pink
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "Populaire",
                        color = AllInTheme.colors.allIn_Pink,
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
                    Text(
                        text = nbPlayers.toString(),
                        color = AllInTheme.colors.allIn_Pink,
                        fontSize = 15.sp,
                        style = AllInTheme.typography.h2
                    )
                    Text(
                        text = " joueurs - ",
                        color = AllInTheme.colors.white,
                        style = AllInTheme.typography.r,
                        fontSize = 15.sp
                    )

                    Text(
                        text = points,
                        color = AllInTheme.colors.allIn_Pink,
                        fontSize = 15.sp,
                        style = AllInTheme.typography.h2
                    )
                    Text(
                        text = " points en jeu",
                        color = AllInTheme.colors.white,
                        style = AllInTheme.typography.r,
                        fontSize = 15.sp
                    )
                }
            }

        }
    }
}

@Preview
@Composable
private fun HomePopularCardsPreview() {
    AllInTheme {
        HomePopularCards(nbPlayers = 12, points = "2.35k", title = "Emre va réussir son TP de CI/CD mercredi?")
    }
}