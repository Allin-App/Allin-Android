package fr.iut.alldev.allin.ui.bet.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.core.AllInCard
import fr.iut.alldev.allin.ui.core.RainbowButton
import fr.iut.alldev.allin.ui.core.bet.BetDateTimeRow
import fr.iut.alldev.allin.ui.core.bet.BetProfilePictureRow
import fr.iut.alldev.allin.ui.core.bet.BetTitleHeader
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun BetScreenCard(
    creator: String,
    category: String,
    title: String,
    date: String,
    time: String,
    players: List<Painter?>,
    modifier: Modifier = Modifier,
    onClickParticipate: ()->Unit
) {
    AllInCard(
        modifier = modifier,
        radius = 16.dp
    ){
        Column(
            Modifier.padding(horizontal = 19.dp, vertical = 11.dp)
        ) {
            BetTitleHeader(
                title = title,
                category = category,
                creator = creator,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(11.dp))
            BetDateTimeRow(label = stringResource(id = R.string.Starting), date = date, time = time)
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = AllInTheme.themeColors.border
        )
        Column(
            Modifier
                .background(AllInTheme.themeColors.background_2)
        ) {
            Row(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(7.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                BetProfilePictureRow(pictures = players)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = pluralStringResource(
                        id = R.plurals.n_players_waiting,
                        players.size,
                        players.size
                    ),
                    style = AllInTheme.typography.m,
                    color = AllInTheme.themeColors.on_background_2
                )
            }
            RainbowButton(
                modifier = Modifier.padding(6.dp),
                text = stringResource(id = R.string.Participate),
                onClick = onClickParticipate
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetScreenCardPreview() {
    AllInTheme {
        BetScreenCard(
            creator = "Lucas",
            category = "Études",
            title = "Emre va réussir son TP de CI/CD mercredi?",
            date = "12 Sept.",
            time = "13:00",
            players = List(3){ null },
            onClickParticipate = {}
        )
    }
}