package fr.iut.alldev.allin.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.ui.core.AllInCard
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun HomePopularCards(
    nbPlayers: Int,
    points: String,
    title: String,
    modifier: Modifier = Modifier
) {
    AllInCard(
        modifier = modifier.fillMaxWidth()
            .padding(6.dp)
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(15)),
        backgroundColor = AllInTheme.colors.allIn_Dark,
        borderWidth = 2.dp,
        borderBrush = AllInTheme.colors.allIn_MainGradient
    ) {
        Column(modifier = Modifier.padding(13.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.LocalFireDepartment,
                    contentDescription = null,
                    tint = AllInTheme.colors.allIn_Pink)
                Text(text = "Populaire",
                    color = AllInTheme.colors.allIn_Pink,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W700
                    )
            }
            Text(text = title,
                color = AllInTheme.colors.white,
                fontSize = 20.sp,
                fontWeight = FontWeight.W800,
                modifier = Modifier.padding(vertical = 22.dp))
            Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                Text(text = nbPlayers.toString(),
                    color = AllInTheme.colors.allIn_Pink,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W700)
                Text(text = " joueurs - ",
                    color = AllInTheme.colors.white,
                    fontSize = 15.sp)

                Text(text = points,
                    color = AllInTheme.colors.allIn_Pink,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W700)
                Text(text = " points en jeu",
                    color = AllInTheme.colors.white,
                    fontSize = 15.sp)
            }
        }
    }
}

@Preview
@Composable
private fun HomePopularCardsPreview() {
    HomePopularCards(nbPlayers = 12, points = "2.35k", title = "Emre va réussir son TP de CI/CD mercredi?")
}