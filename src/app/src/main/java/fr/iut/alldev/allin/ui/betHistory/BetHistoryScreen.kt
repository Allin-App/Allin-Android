package fr.iut.alldev.allin.ui.betHistory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.ext.formatToMediumDateNoYear
import fr.iut.alldev.allin.data.ext.formatToTime
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.betHistory.components.BetHistoryScreenCard

@Composable
fun BetHistoryScreen(
    isCurrent: Boolean,
    viewModel: BetHistoryViewModel = hiltViewModel(),
) {
    val bets by viewModel.bets.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        item {
            Text(
                text = stringResource(
                    id = if (isCurrent) R.string.bet_history_current_title
                    else R.string.bet_history_title
                ),
                style = AllInTheme.typography.h1,
                color = AllInTheme.colors.allInGrey,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        bets?.let { bets ->
            items(bets) {
                BetHistoryScreenCard(
                    title = it.phrase,
                    creator = it.creator,
                    category = it.theme,
                    date = it.endRegisterDate.formatToMediumDateNoYear(),
                    time = it.endRegisterDate.formatToTime(),
                    status = it.betStatus,
                    nbCoins = 230
                )
            }
        }
    }
}