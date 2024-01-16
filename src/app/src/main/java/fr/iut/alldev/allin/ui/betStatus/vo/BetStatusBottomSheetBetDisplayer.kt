package fr.iut.alldev.allin.ui.betStatus.vo

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.ext.formatToMediumDateNoYear
import fr.iut.alldev.allin.data.ext.formatToTime
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.ext.getDateEndLabelId
import fr.iut.alldev.allin.ext.getDateStartLabelId
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.betStatus.components.BetStatusWinner
import fr.iut.alldev.allin.ui.betStatus.components.YesNoDetailsLine
import fr.iut.alldev.allin.ui.betStatus.components.YesNoStatBar
import fr.iut.alldev.allin.ui.core.AllInDetailsDrawer
import fr.iut.alldev.allin.ui.core.RainbowButton
import fr.iut.alldev.allin.ui.core.bet.BetDateTimeRow
import fr.iut.alldev.allin.ui.core.bet.BetTitleHeader
import fr.iut.alldev.allin.ui.preview.BetWithStatusPreviewProvider
import fr.iut.alldev.allin.vo.bet.BetDisplayer

class BetStatusBottomSheetBetDisplayer(
    val openParticipateSheet: () -> Unit
) : BetDisplayer {
    val paddingValues = mutableStateOf(PaddingValues())

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun DisplayYesNoBet(bet: YesNoBet) {
        val safeBottomPadding = paddingValues.value.calculateBottomPadding()

        Box(Modifier.padding(bottom = safeBottomPadding)) {
            Column {
                Column(Modifier.padding(horizontal = 20.dp)) {
                    BetTitleHeader(
                        title = bet.phrase,
                        category = bet.theme,
                        creator = bet.creator,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        BetDateTimeRow(
                            label = stringResource(id = bet.betStatus.getDateStartLabelId()),
                            date = bet.endRegisterDate.formatToMediumDateNoYear(),
                            time = bet.endRegisterDate.formatToTime(),
                            modifier = Modifier.width(IntrinsicSize.Max)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        BetDateTimeRow(
                            label = stringResource(id = bet.betStatus.getDateEndLabelId()),
                            date = bet.endBetDate.formatToMediumDateNoYear(),
                            time = bet.endBetDate.formatToTime(),
                            modifier = Modifier.width(IntrinsicSize.Max)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                if (bet.betStatus is BetStatus.Finished) {
                    BetStatusWinner(
                        answer = stringResource(id = R.string.Yes),
                        color = AllInTheme.colors.allInBlue,
                        coinAmount = 442,
                        username = "Imri",
                        multiplier = 1.2f
                    )
                } else {
                    HorizontalDivider(color = AllInTheme.themeColors.border)
                }
                Column(
                    Modifier
                        .fillMaxHeight()
                        .background(AllInTheme.themeColors.background2)
                        .padding(horizontal = 20.dp)
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    YesNoStatBar(yesPercentage = .86f)
                    AllInDetailsDrawer {
                        YesNoDetailsLine(
                            icon = AllInTheme.icons.allCoins(),
                            yesText = "550",
                            noText = "330",
                        )
                        YesNoDetailsLine(
                            icon = Icons.Filled.People,
                            yesText = "12",
                            noText = "5"
                        )
                        YesNoDetailsLine(
                            icon = Icons.Filled.WorkspacePremium,
                            yesText = "45",
                            noText = "45"
                        )
                        YesNoDetailsLine(
                            icon = Icons.Filled.EmojiEvents,
                            yesText = "x1.2",
                            noText = "x1.45"
                        )
                    }
                }
            }
            if (bet.betStatus !is BetStatus.Finished) {
                RainbowButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 7.dp),
                    text = stringResource(id = R.string.Participate),
                    enabled = bet.betStatus == BetStatus.Waiting,
                    onClick = openParticipateSheet
                )
            }
        }
    }

    @Composable
    override fun DisplayMatchBet(bet: MatchBet) {
        Text("This is a MATCH BET")
    }

    @Composable
    override fun DisplayCustomBet(bet: CustomBet) {
        Text("This is a CUSTOM BET")
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetStatusBottomSheetPreview(
    @PreviewParameter(BetWithStatusPreviewProvider::class) bet: Bet
) {
    AllInTheme {
        val coins = remember { mutableIntStateOf(100) }
        BetStatusBottomSheetBetDisplayer {}.DisplayBet(bet)
    }
}