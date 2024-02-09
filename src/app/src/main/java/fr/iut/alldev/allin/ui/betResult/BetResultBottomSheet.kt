package fr.iut.alldev.allin.ui.betResult

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.ext.formatToMediumDateNoYear
import fr.iut.alldev.allin.data.ext.formatToTime
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.betResult.components.BetResultBottomSheetBetCard
import fr.iut.alldev.allin.ui.betResult.components.BetResultBottomSheetContentCoinAmount
import fr.iut.alldev.allin.ui.betResult.components.BetResultBottomSheetContentCongratulations
import fr.iut.alldev.allin.ui.core.AllInBottomSheet
import fr.iut.alldev.allin.ui.core.AllInMarqueeBox
import java.time.ZonedDateTime

@Composable
fun BetResultBottomSheet(
    state: SheetState,
    sheetVisibility: Boolean,
    username: String,
    coinAmount: Int,
    bet: Bet,
    stake: Int,
    winnings: Int,
    odds: Float,
    onDismiss: () -> Unit
) {
    AllInBottomSheet(
        sheetVisibility = sheetVisibility,
        onDismiss = onDismiss,
        state = state,
        dragHandle = null
    ) {
        BetResultBottomSheetContent(
            username = username,
            coinAmount = coinAmount,
            bet = bet,
            stake = stake,
            winnings = winnings,
            odds = odds,
            onClose = onDismiss
        )
    }
}

@Composable
fun BetResultBottomSheetContent(
    username: String,
    coinAmount: Int,
    bet: Bet,
    stake: Int,
    winnings: Int,
    odds: Float,
    onClose: () -> Unit
) {
    AllInMarqueeBox(backgroundBrush = AllInTheme.colors.allInMainGradientReverse) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            IconButton(
                onClick = onClose,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    tint = AllInTheme.colors.white,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }

            Icon(
                painter = painterResource(R.drawable.allin),
                contentDescription = null,
                tint = AllInTheme.colors.white,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopCenter)
            )

            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                BetResultBottomSheetContentCongratulations(username = username)
                BetResultBottomSheetContentCoinAmount(amount = coinAmount)
                BetResultBottomSheetBetCard(
                    title = bet.phrase,
                    creator = bet.creator,
                    category = bet.theme,
                    date = bet.endBetDate.formatToMediumDateNoYear(),
                    time = bet.endBetDate.formatToTime(),
                    status = bet.betStatus,
                    stake = stake,
                    winnings = winnings,
                    odds = odds

                )
            }
        }
    }
}

@Preview
@Composable
private fun BetResultBottomSheetContentPreview() {
    AllInTheme {
        BetResultBottomSheetContent(
            username = "Pseudo",
            coinAmount = 3976,
            bet = YesNoBet(
                id = "1",
                theme = "Theme",
                phrase = "Phrase",
                endRegisterDate = ZonedDateTime.now(),
                endBetDate = ZonedDateTime.now(),
                isPublic = true,
                betStatus = BetStatus.IN_PROGRESS,
                creator = "creator",
            ),
            stake = 4175,
            winnings = 2600,
            odds = 6.7f
        ) {

        }
    }
}