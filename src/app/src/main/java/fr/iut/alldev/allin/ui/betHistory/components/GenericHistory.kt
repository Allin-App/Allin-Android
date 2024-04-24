package fr.iut.alldev.allin.ui.betHistory.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.ext.asPaddingValues
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun <T> GenericHistory(
    title: String,
    bets: List<T>,
    getTitle: (T) -> String,
    getCreator: (T) -> String,
    getCategory: (T) -> String,
    getEndRegisterDate: (T) -> String,
    getEndBetTime: (T) -> String,
    getStatus: (T) -> BetStatus,
    getNbCoins: (T) -> Int,
    getWon: (T) -> Boolean,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = WindowInsets.navigationBars.asPaddingValues(horizontal = 24.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        item {
            Text(
                text = title,
                style = AllInTheme.typography.h1,
                color = AllInColorToken.allInGrey,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        items(bets) {
            BetHistoryScreenCard(
                title = getTitle(it),
                creator = getCreator(it),
                category = getCategory(it),
                date = getEndRegisterDate(it),
                time = getEndBetTime(it),
                status = getStatus(it),
                nbCoins = getNbCoins(it),
                won = getWon(it)
            )
        }
    }
}