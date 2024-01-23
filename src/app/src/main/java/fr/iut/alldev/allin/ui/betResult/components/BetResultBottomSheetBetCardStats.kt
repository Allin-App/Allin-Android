package fr.iut.alldev.allin.ui.betResult.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInCard
import fr.iut.alldev.allin.ui.core.AllInCoinCount
import fr.iut.alldev.allin.ui.core.IconPosition

@Composable
fun BetResultBottomSheetBetCardStats(
    stake: Int,
    winnings: Int,
    odds: Float
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(AllInTheme.themeColors.background2)
            .padding(horizontal = 19.dp, vertical = 11.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.bet_result_stake),
                style = AllInTheme.typography.sm2,
                color = AllInTheme.themeColors.onMainSurface
            )

            AllInCoinCount(
                amount = stake,
                color = AllInTheme.colors.allInPurple,
                textStyle = AllInTheme.typography.sm1,
                position = IconPosition.TRAILING
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.bet_result_winnings),
                style = AllInTheme.typography.sm2,
                color = AllInTheme.colors.allInPurple
            )
            AllInCoinCount(
                amount = winnings,
                textStyle = AllInTheme.typography.sm1,
                brush = AllInTheme.colors.allInMainGradient,
                position = IconPosition.TRAILING
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.bet_result_odds),
                style = AllInTheme.typography.sm2,
                color = AllInTheme.themeColors.onBackground2
            )
            AllInCard(
                radius = 8.dp,
                backgroundBrush = AllInTheme.colors.allInMainGradient
            ) {
                Box(Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
                    Text(
                        text = "$odds",
                        style = AllInTheme.typography.sm1,
                        color = AllInTheme.colors.white
                    )
                }
            }
        }
    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetResultBottomSheetBetCardStatsPreview() {
    AllInTheme {
        BetResultBottomSheetBetCardStats(
            stake = 2446,
            winnings = 6930,
            odds = 2.3f
        )
    }
}