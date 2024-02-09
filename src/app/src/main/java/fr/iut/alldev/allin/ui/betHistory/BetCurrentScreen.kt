package fr.iut.alldev.allin.ui.betHistory

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.ext.formatToMediumDateNoYear
import fr.iut.alldev.allin.data.ext.formatToTime
import fr.iut.alldev.allin.ui.betHistory.components.GenericHistory

@Composable
fun BetCurrentScreen(
    viewModel: BetCurrentViewModel = hiltViewModel()
) {
    val bets by viewModel.bets.collectAsState()
    GenericHistory(
        title = stringResource(id = R.string.bet_history_current_title),
        bets = bets,
        getTitle = { it.bet.phrase },
        getCreator = { it.bet.creator },
        getCategory = { it.bet.theme },
        getEndRegisterDate = { it.bet.endRegisterDate.formatToMediumDateNoYear() },
        getEndBetTime = { it.bet.endBetDate.formatToTime() },
        getStatus = { it.bet.betStatus },
        getNbCoins = { it.userParticipation?.stake ?: 0 },
        getWon = { true }
    )
}