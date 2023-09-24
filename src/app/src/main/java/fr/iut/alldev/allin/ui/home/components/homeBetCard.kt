package fr.iut.alldev.allin.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.core.AllInCard
import fr.iut.alldev.allin.ui.core.HighlightedText
import fr.iut.alldev.allin.ui.core.ProfilePicture
import fr.iut.alldev.allin.ui.core.RainbowButton
import fr.iut.alldev.allin.ui.theme.AllInRippleTheme
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun HomeBetCard(
    creator: String,
    category: String,
    title: String,
    date: String,
    time: String,
    nbPlayer: Int,
    modifier: Modifier = Modifier,
    onClickParticipate: ()->Unit
) {
    AllInCard(
        modifier = modifier,
        radius = 7
    ){
        Column(Modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .align(Alignment.End)
                    .padding(top = 12.dp, end = 10.dp)) {
                HighlightedText(
                    text = stringResource(id = R.string.Proposed_by_x, creator),
                    query = creator,
                    highlightStyle = SpanStyle(fontWeight = FontWeight.Bold, color = AllInTheme.colors.allIn_Dark),
                    fontSize = 12.sp,
                    style = AllInTheme.typography.s,
                    color = AllInTheme.colors.allIn_LightGrey300
                )
            }
            Column(Modifier.padding(horizontal = 19.dp, vertical = 11.dp)) {
                Text(
                    text = category,
                    fontSize = 15.sp,
                    color = AllInTheme.colors.allIn_LightGrey300,
                    style = AllInTheme.typography.m
                )
                Text(
                    text = title,
                    fontSize = 20.sp,
                    style = AllInTheme.typography.h1
                )
                Spacer(modifier = Modifier.height(11.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(id = R.string.Starting),
                        fontSize = 15.sp,
                        style = AllInTheme.typography.m,
                        color = AllInTheme.colors.allIn_LightGrey300,
                    )
                    HomeBetCardDateTimeChip(
                        text = date,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    HomeBetCardDateTimeChip(time)
                }
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = AllInTheme.colors.allIn_LightGrey100
            )
            Column(
                Modifier
                    .background(AllInTheme.colors.allIn_LightGrey50)
            ) {
                Row(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(7.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    val nRepeat = if (nbPlayer > 5) 5 else nbPlayer
                    Box(
                        Modifier.width((nRepeat*15).dp)
                    ){
                        repeat(nRepeat) {
                            ProfilePicture(
                                size = 30.dp,
                                modifier = Modifier
                                    .align(CenterEnd)
                                    .offset(x = (it * -15).dp)
                                    .zIndex(-it.toFloat())
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = pluralStringResource(
                            id = R.plurals.n_players_waiting,
                            nbPlayer,
                            nbPlayer
                        ),
                        style = AllInTheme.typography.m,
                        color = AllInTheme.colors.allIn_LightGrey300
                    )
                }
                CompositionLocalProvider(
                    LocalRippleTheme provides AllInRippleTheme,
                ){
                    RainbowButton(
                        modifier = Modifier.padding(6.dp),
                        text = stringResource(id = R.string.Participate),
                        onClick = onClickParticipate
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeBetCardPreview() {
    AllInTheme {
        HomeBetCard(
            creator = "Lucas",
            category = "Études",
            title = "Emre va réussir son TP de CI/CD mercredi?",
            date = "12 Sept.",
            time = "13:00",
            nbPlayer = 4,
            onClickParticipate = {}
        )
    }
}