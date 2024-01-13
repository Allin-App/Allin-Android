package fr.iut.alldev.allin.ui.betResult.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.alldev.allin.data.model.bet.BetFinishedStatus
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.bet.BetCard

@Composable
fun BetResultBottomSheetBetCard(
    modifier: Modifier = Modifier,
    title: String,
    creator: String,
    category: String,
    date: String,
    time: String,
    status: BetStatus,
    stake: Int,
    winnings: Int,
    odds: Float
) {
    BetCard(
        title = title,
        creator = creator,
        category = category,
        date = date,
        time = time,
        status = status,
        modifier = modifier
    ) {
        BetResultBottomSheetBetCardStats(
            stake = stake,
            winnings = winnings,
            odds = odds
        )
    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetResultBottomSheetBetCardPreview() {
    AllInTheme {
        BetResultBottomSheetBetCard(
            creator = "Creator",
            category = "Category",
            title = "Title",
            date = "Date",
            time = "Time",
            status = BetStatus.Finished(BetFinishedStatus.WON),
            stake = 2446,
            winnings = 6930,
            odds = 2.3f
        )
    }
}