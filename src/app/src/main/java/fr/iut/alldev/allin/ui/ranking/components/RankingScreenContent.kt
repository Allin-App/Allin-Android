package fr.iut.alldev.allin.ui.ranking.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.ext.asPaddingValues
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.bet.AllInEmptyView

@Composable
fun RankingScreenContent(
    users: List<User>
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = WindowInsets.navigationBars.asPaddingValues(start = 24.dp, end = 24.dp, top = 18.dp),
        verticalArrangement = Arrangement.spacedBy(11.dp),
    ) {
        item {
            Text(
                text = stringResource(id = R.string.ranking_title),
                style = AllInTheme.typography.h1,
                color = AllInColorToken.allInGrey,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(11.dp)
            ) {
                users.firstOrNull()?.let { first ->
                    RankingScreenFirst(
                        username = first.username,
                        coins = first.coins,
                        image = first.image,
                        modifier = Modifier.weight(1f)
                    )
                }

                users.getOrNull(1)?.let { second ->
                    RankingScreenSecond(
                        username = second.username,
                        coins = second.coins,
                        image = second.image,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        if (users.size > 2) {
            itemsIndexed(users.subList(2, users.size), key = { _, user -> user.id }) { idx, user ->
                RankingScreenItem(
                    position = idx + 3,
                    username = user.username,
                    image = user.image,
                    coins = user.coins
                )
            }
        }

        if (users.size == 1) {
            item {
                AllInEmptyView(
                    text = stringResource(id = R.string.ranking_empty_text),
                    subtext = stringResource(id = R.string.ranking_empty_subtext),
                    image = painterResource(id = R.drawable.eyes),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun RankingScreenContentPreview() {
    AllInTheme {
        RankingScreenContent(
            users = listOf(
                User(
                    id = "1",
                    username = "Owen",
                    email = "",
                    coins = 8533,
                    nbBets = 0,
                    nbFriends = 0,
                    bestWin = 0
                ),
                User(
                    id = "2",
                    username = "Dave",
                    email = "",
                    coins = 6942,
                    nbBets = 0,
                    nbFriends = 0,
                    bestWin = 0
                ),
                User(
                    id = "3",
                    username = "Lucas",
                    email = "",
                    coins = 3333,
                    nbBets = 0,
                    nbFriends = 0,
                    bestWin = 0
                ),
                User(
                    id = "4",
                    username = "Louison",
                    email = "",
                    coins = 1970,
                    nbBets = 0,
                    nbFriends = 0,
                    bestWin = 0
                ),
                User(
                    id = "5",
                    username = "Imri",
                    email = "",
                    coins = 1,
                    nbBets = 0,
                    nbFriends = 0,
                    bestWin = 0
                )
            )
        )
    }
}

@Preview
@Composable
private fun RankingScreenContentEmptyPreview() {
    AllInTheme {
        RankingScreenContent(
            users = listOf(
                User(
                    id = "1",
                    username = "Owen",
                    email = "",
                    coins = 8533,
                    nbBets = 0,
                    nbFriends = 0,
                    bestWin = 0
                )
            )
        )
    }
}