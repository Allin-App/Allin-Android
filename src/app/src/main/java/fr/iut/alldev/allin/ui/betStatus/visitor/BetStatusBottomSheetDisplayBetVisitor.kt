package fr.iut.alldev.allin.ui.betStatus.visitor

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
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.ext.formatToMediumDateNoYear
import fr.iut.alldev.allin.data.ext.formatToTime
import fr.iut.alldev.allin.data.model.bet.BetFinishedStatus
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.ext.getDateEndLabelId
import fr.iut.alldev.allin.ext.getDateStartLabelId
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.betStatus.components.BetStatusParticipationBottomSheet
import fr.iut.alldev.allin.ui.betStatus.components.BetStatusWinner
import fr.iut.alldev.allin.ui.betStatus.components.YesNoDetailsLine
import fr.iut.alldev.allin.ui.betStatus.components.YesNoStatBar
import fr.iut.alldev.allin.ui.core.AllInDetailsDrawer
import fr.iut.alldev.allin.ui.core.RainbowButton
import fr.iut.alldev.allin.ui.core.bet.BetDateTimeRow
import fr.iut.alldev.allin.ui.core.bet.BetTitleHeader
import fr.iut.alldev.allin.vo.bet.factory.toBetVO
import fr.iut.alldev.allin.vo.bet.visitor.DisplayBetVisitor
import java.time.ZonedDateTime

class BetStatusBottomSheetDisplayBetVisitor(
    val userCoinAmount: MutableIntState,
    val onParticipate: (Int) -> Unit,
) : DisplayBetVisitor {

    val participateBottomSheetVisibility = mutableStateOf(false)
    val paddingValues = mutableStateOf(PaddingValues())

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun VisitYesNoBet(b: YesNoBet) {

        val (participateSheetVisibility, setParticipateSheetVisibility) = remember {
            this.participateBottomSheetVisibility
        }

        val safeBottomPadding = paddingValues.value.calculateBottomPadding()

        Box(
            Modifier
                .padding(bottom = safeBottomPadding)
        ) {
            Column {
                Column(Modifier.padding(horizontal = 20.dp)) {
                    BetTitleHeader(
                        title = b.phrase,
                        category = b.theme,
                        creator = "Lucas" /*TODO : Creator*/,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        BetDateTimeRow(
                            label = stringResource(id = b.betStatus.getDateStartLabelId()),
                            date = b.endRegisterDate.formatToMediumDateNoYear(),
                            time = b.endRegisterDate.formatToTime(),
                            modifier = Modifier.width(IntrinsicSize.Max)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        BetDateTimeRow(
                            label = stringResource(id = b.betStatus.getDateEndLabelId()),
                            date = b.endBetDate.formatToMediumDateNoYear(),
                            time = b.endBetDate.formatToTime(),
                            modifier = Modifier.width(IntrinsicSize.Max)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                if (b.betStatus is BetStatus.Finished) {
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
            if (b.betStatus !is BetStatus.Finished) {
                RainbowButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 7.dp),
                    text = stringResource(id = R.string.Participate),
                    enabled = b.betStatus == BetStatus.Waiting,
                    onClick = {
                        setParticipateSheetVisibility(true)
                    }
                )
            }
        }

        BetStatusParticipationBottomSheet(
            sheetVisibility = participateSheetVisibility && b.betStatus == BetStatus.Waiting,
            safeBottomPadding = safeBottomPadding,
            betPhrase = b.phrase,
            coinAmount = userCoinAmount.intValue,
            onDismiss = { setParticipateSheetVisibility(false) },
            state = rememberModalBottomSheetState()
        ) {
            onParticipate(100)
        }
    }

    @Composable
    override fun VisitMatchBet(b: MatchBet) {
        Text("This is a MATCH BET")
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun YesNoBetPreview() {
    AllInTheme {
        val coins = remember { mutableIntStateOf(100) }
        YesNoBet(
            theme = "Theme",
            phrase = "Phrase",
            endRegisterDate = ZonedDateTime.now(),
            endBetDate = ZonedDateTime.now(),
            isPublic = true,
            betStatus = BetStatus.InProgress
        ).toBetVO()?.Accept(
            BetStatusBottomSheetDisplayBetVisitor(
                userCoinAmount = coins,
                onParticipate = {}
            )
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun YesNoBetFinishedPreview() {
    AllInTheme {
        val coins = remember { mutableIntStateOf(100) }
        YesNoBet(
            theme = "Theme",
            phrase = "Phrase",
            endRegisterDate = ZonedDateTime.now(),
            endBetDate = ZonedDateTime.now(),
            isPublic = true,
            betStatus = BetStatus.Finished(BetFinishedStatus.WON)
        ).toBetVO()?.Accept(
            BetStatusBottomSheetDisplayBetVisitor(
                userCoinAmount = coins,
                onParticipate = {}
            )
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MatchBetPreview() {
    AllInTheme {
        val coins = remember { mutableIntStateOf(100) }
        MatchBet(
            theme = "Theme",
            phrase = "Phrase",
            endRegisterDate = ZonedDateTime.now(),
            endBetDate = ZonedDateTime.now(),
            isPublic = true,
            betStatus = BetStatus.InProgress,
            nameTeam1 = "Team 1",
            nameTeam2 = "Team 2"
        ).toBetVO()?.Accept(
            BetStatusBottomSheetDisplayBetVisitor(
                userCoinAmount = coins,
                onParticipate = {}
            )
        )
    }
}