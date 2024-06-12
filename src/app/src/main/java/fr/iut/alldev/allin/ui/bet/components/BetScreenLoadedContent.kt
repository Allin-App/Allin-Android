package fr.iut.alldev.allin.ui.bet.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.ext.formatToMediumDateNoYear
import fr.iut.alldev.allin.data.ext.formatToTime
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BetFilter
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.BinaryBet
import fr.iut.alldev.allin.ext.textId
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInChip
import fr.iut.alldev.allin.ui.core.bet.AllInEmptyView
import java.time.ZonedDateTime

private const val DISABLED_OPACITY = .5f

@Composable
fun BetScreenLoadedContent(
    popularBet: Bet?,
    filters: List<BetFilter>,
    bets: List<Bet>,
    isRefreshing: Boolean,
    selectBet: (Bet, Boolean) -> Unit,
    toggleFilter: (BetFilter) -> Unit,
    refreshData: () -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, refreshData)
    val progressAnimation by animateFloatAsState(pullRefreshState.progress * 15, label = "")

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .pullRefresh(pullRefreshState)
                .padding(top = with(LocalDensity.current) {
                    progressAnimation.toDp()
                }),
            contentPadding = WindowInsets.navigationBars.asPaddingValues(),
        ) {
            popularBet?.let {
                item {
                    Box(Modifier.fillMaxWidth()) {
                        BetScreenPopularCard(
                            nbPlayers = it.totalParticipants,
                            points = it.totalStakes,
                            title = it.phrase,
                            onClick = { selectBet(it, false) },
                            enabled = !isRefreshing,
                            modifier = Modifier
                                .padding(top = 13.dp, bottom = 10.dp)
                                .padding(horizontal = 13.dp)
                                .let {
                                    if (isRefreshing) it.alpha(DISABLED_OPACITY)
                                    else it
                                }
                        )
                    }

                }
            }
            stickyHeader {
                LazyRow(
                    modifier = Modifier
                        .background(
                            Brush.verticalGradient(
                                0.5f to AllInTheme.colors.mainSurface,
                                1f to Color.Transparent
                            )
                        )
                        .zIndex(1f),
                    horizontalArrangement = Arrangement.spacedBy(9.dp),
                    contentPadding = PaddingValues(horizontal = 23.dp)
                ) {
                    items(BetFilter.entries) {
                        val isSelected by remember(filters) {
                            derivedStateOf {
                                filters.contains(it)
                            }
                        }
                        AllInChip(
                            text = stringResource(id = it.textId()),
                            isSelected = isSelected,
                            onClick = { toggleFilter(it) },
                            enabled = !isRefreshing,
                            modifier = Modifier.let {
                                if (isRefreshing) it.alpha(DISABLED_OPACITY)
                                else it
                            }
                        )
                    }
                }
            }
            itemsIndexed(
                items = bets,
                key = { _, it -> it.id }
            ) { idx, it ->
                BetScreenCard(
                    creator = it.creator,
                    category = it.theme,
                    title = it.phrase,
                    date = it.endRegisterDate.formatToMediumDateNoYear(),
                    time = it.endRegisterDate.formatToTime(),
                    players = emptyList(), // TODO : Players
                    totalParticipants = it.totalParticipants,
                    onClickParticipate = { selectBet(it, true) },
                    onClickCard = { selectBet(it, false) },
                    enabled = !isRefreshing,
                    modifier = Modifier
                        .animateItemPlacement()
                        .padding(horizontal = 23.dp)
                        .let {
                            if (isRefreshing) it.alpha(DISABLED_OPACITY)
                            else it
                        }
                )

                if (idx != bets.lastIndex) {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            if (bets.isEmpty()) {
                item {
                    AllInEmptyView(
                        text = stringResource(id = R.string.bet_empty_text),
                        subtext = null,
                        image = painterResource(id = R.drawable.video_game),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillParentMaxHeight(.5f)
                    )
                }
            }
        }

        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = isRefreshing,
            state = pullRefreshState
        )
    }
}

@Preview
@Composable
private fun BetScreenLoadedContentPreview() {
    AllInTheme {
        BetScreenLoadedContent(
            popularBet = BinaryBet(
                id = "Arleen",
                creator = "Omar",
                theme = "Kyli",
                phrase = "Leigha",
                endRegisterDate = ZonedDateTime.now(),
                endBetDate = ZonedDateTime.now(),
                isPublic = false,
                betStatus = BetStatus.IN_PROGRESS,
                totalParticipants = 200,
                totalStakes = 2500
            ),
            filters = emptyList(),
            bets = emptyList(),
            isRefreshing = true,
            selectBet = { _, _ -> },
            toggleFilter = { },
            refreshData = { }
        )
    }

}