package fr.iut.alldev.allin.ui.bet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.ui.bet.components.BetScreenLoadedContent
import fr.iut.alldev.allin.ui.core.AllInLoading

@Composable
fun BetScreen(
    viewModel: BetViewModel = hiltViewModel(),
    selectBet: (Bet, Boolean) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val filters by viewModel.filters.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.refreshing.collectAsStateWithLifecycle()

    when (val s = state) {
        is BetViewModel.State.Loaded -> {
            BetScreenLoadedContent(
                popularBet = s.popularBet,
                filters = filters,
                bets = s.bets,
                isRefreshing = isRefreshing,
                selectBet = selectBet,
                toggleFilter = { viewModel.toggleFilter(it) },
                refreshData = { viewModel.refreshData() }
            )
        }

        BetViewModel.State.Loading -> {
            AllInLoading(visible = true)
        }
    }
}