package fr.iut.alldev.allin.ui.bet

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.ext.formatToMediumDateNoYear
import fr.iut.alldev.allin.data.ext.formatToTime
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.bet.components.BetScreenCard
import fr.iut.alldev.allin.ui.bet.components.BetScreenPopularCard
import fr.iut.alldev.allin.ui.core.AllInChip

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun BetScreen(
    viewModel: BetViewModel = hiltViewModel(),
    selectBet: (Bet, Boolean) -> Unit,
) {
    val haptic = LocalHapticFeedback.current

    val bets by viewModel.bets.collectAsState()

    val refreshing by viewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(refreshing, { viewModel.refresh() })
    val progressAnimation by animateFloatAsState(pullRefreshState.progress * 15, label = "")

    LazyColumn(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .padding(top = with(LocalDensity.current) {
                progressAnimation.toDp()
            })
    ) {
        item {
            Box(
                Modifier.fillMaxWidth()
            ) {
                BetScreenPopularCard(
                    modifier = Modifier
                        .padding(top = 13.dp, bottom = 10.dp)
                        .padding(horizontal = 13.dp),
                    nbPlayers = 12,
                    points = 2.35f,
                    pointUnit = "k",
                    title = "Emre va réussir son TP de CI/CD mercredi?"
                )
                PullRefreshIndicator(
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    refreshing = refreshing,
                    state = pullRefreshState
                )
            }
        }
        stickyHeader {
            LazyRow(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            0.5f to AllInTheme.themeColors.mainSurface,
                            1f to Color.Transparent
                        )
                    )
                    .padding(top = 5.dp, bottom = 19.dp),
                horizontalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.width(23.dp))
                }
                items(items) {
                    var isSelected by remember { mutableStateOf(false) }
                    AllInChip(
                        text = stringResource(id = it),
                        isSelected = isSelected,
                        onClick = {
                            isSelected = !isSelected
                        })
                }
                item {
                    Spacer(modifier = Modifier.width(23.dp))
                }
            }
        }
        itemsIndexed(bets) { idx, it ->
            BetScreenCard(
                creator = it.creator,
                category = it.theme,
                title = it.phrase,
                date = it.endRegisterDate.formatToMediumDateNoYear(),
                time = it.endRegisterDate.formatToTime(),
                players = List(3) { null },
                onClickParticipate = { selectBet(it, true) },
                onClickCard = { selectBet(it, false) },
                modifier = Modifier.padding(horizontal = 23.dp)
            )
            if (idx != bets.lastIndex) {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        item {
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}

val items = listOf(
    R.string.Public,
    R.string.Invitation,
    R.string.Current,
    R.string.Finished
)