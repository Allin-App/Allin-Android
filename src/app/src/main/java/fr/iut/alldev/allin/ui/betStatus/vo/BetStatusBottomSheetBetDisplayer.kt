package fr.iut.alldev.allin.ui.betStatus.vo

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.ext.formatToMediumDateNoYear
import fr.iut.alldev.allin.data.ext.formatToTime
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.NO_VALUE
import fr.iut.alldev.allin.data.model.bet.YES_VALUE
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import fr.iut.alldev.allin.ext.formatToSimple
import fr.iut.alldev.allin.ext.getDateEndLabelId
import fr.iut.alldev.allin.ext.getDateStartLabelId
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.betStatus.components.BetStatusWinner
import fr.iut.alldev.allin.ui.betStatus.components.BinaryStatBar
import fr.iut.alldev.allin.ui.betStatus.components.YesNoDetailsLine
import fr.iut.alldev.allin.ui.core.AllInCoinCount
import fr.iut.alldev.allin.ui.core.AllInDetailsDrawer
import fr.iut.alldev.allin.ui.core.ProfilePicture
import fr.iut.alldev.allin.ui.core.RainbowButton
import fr.iut.alldev.allin.ui.core.bet.BetDateTimeRow
import fr.iut.alldev.allin.ui.core.bet.BetTitleHeader
import fr.iut.alldev.allin.ui.preview.BetDetailPreviewProvider
import fr.iut.alldev.allin.vo.bet.BetDisplayer
import java.util.Locale

class BetStatusBottomSheetBetDisplayer(
    val openParticipateSheet: () -> Unit
) : BetDisplayer {
    val paddingValues = mutableStateOf(PaddingValues())

    @Composable
    private fun DisplayBinaryBet(
        betDetail: BetDetail,
        response1: String,
        response2: String,
        response1Display: String = response1,
        response2Display: String = response2
    ) {
        val safeBottomPadding = paddingValues.value.calculateBottomPadding()
        val configuration = LocalConfiguration.current
        val locale =
            remember { ConfigurationCompat.getLocales(configuration).get(0) ?: Locale.getDefault() }

        val response1Answer = remember { betDetail.getAnswerOfResponse(response1) }
        val response2Answer = remember { betDetail.getAnswerOfResponse(response2) }

        Box(Modifier.padding(bottom = safeBottomPadding)) {
            Column {
                Column(Modifier.padding(horizontal = 20.dp)) {
                    BetTitleHeader(
                        title = betDetail.bet.phrase,
                        category = betDetail.bet.theme,
                        creator = betDetail.bet.creator,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        BetDateTimeRow(
                            label = stringResource(id = betDetail.bet.betStatus.getDateStartLabelId()),
                            date = betDetail.bet.endRegisterDate.formatToMediumDateNoYear(),
                            time = betDetail.bet.endRegisterDate.formatToTime(),
                            modifier = Modifier.width(IntrinsicSize.Max)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        BetDateTimeRow(
                            label = stringResource(id = betDetail.bet.betStatus.getDateEndLabelId()),
                            date = betDetail.bet.endBetDate.formatToMediumDateNoYear(),
                            time = betDetail.bet.endBetDate.formatToTime(),
                            modifier = Modifier.width(IntrinsicSize.Max)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                if (betDetail.bet.betStatus == BetStatus.FINISHED) {
                    BetStatusWinner(
                        answer = response1Display,
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
                    BinaryStatBar(
                        response1Percentage = remember {
                            val total = (response1Answer?.totalParticipants
                                ?: 0) + (response2Answer?.totalParticipants ?: 0)
                            if (total == 0) .5f else (response1Answer?.totalParticipants
                                ?: 0) / total.toFloat()
                        },
                        response1 = response1Display,
                        response2 = response2Display
                    )
                    AllInDetailsDrawer {
                        YesNoDetailsLine(
                            icon = AllInTheme.icons.allCoins(),
                            yesText = response1Answer?.totalStakes?.toString() ?: "0",
                            noText = response2Answer?.totalStakes?.toString() ?: "0"
                        )
                        YesNoDetailsLine(
                            icon = rememberVectorPainter(image = Icons.Filled.People),
                            yesText = response1Answer?.totalParticipants?.toString() ?: "0",
                            noText = response2Answer?.totalParticipants?.toString() ?: "0"
                        )
                        YesNoDetailsLine(
                            icon = rememberVectorPainter(image = Icons.Filled.WorkspacePremium),
                            yesText = response1Answer?.highestStake?.toString() ?: "0",
                            noText = response2Answer?.highestStake?.toString() ?: "0"
                        )
                        YesNoDetailsLine(
                            icon = rememberVectorPainter(image = Icons.Filled.EmojiEvents),
                            yesText = "x${response1Answer?.odds?.formatToSimple(locale) ?: "1.00"}",
                            noText = "x${response2Answer?.odds?.formatToSimple(locale) ?: "1.00"}"
                        )
                    }

                    Text(
                        text = stringResource(id = R.string.bet_status_participants_list),
                        fontSize = 20.sp,
                        color = AllInTheme.themeColors.onMainSurface,
                        style = AllInTheme.typography.h1,
                        modifier = Modifier.padding(vertical = 36.dp)
                    )
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(horizontal = 13.dp, vertical = 8.dp),
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        betDetail.userParticipation?.let {
                            item {
                                BetStatusParticipant(
                                    username = it.username,
                                    allCoinsAmount = it.stake
                                )
                                HorizontalDivider(
                                    color = AllInTheme.themeColors.border,
                                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 25.dp)
                                )
                            }
                        }
                        items(betDetail.participations) {
                            if (it.username != betDetail.userParticipation?.username) {
                                BetStatusParticipant(
                                    username = it.username,
                                    allCoinsAmount = it.stake
                                )
                            }
                        }
                    }
                }
            }
            if (betDetail.bet.betStatus != BetStatus.FINISHED && betDetail.userParticipation == null) {
                RainbowButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 7.dp),
                    text = stringResource(id = R.string.Participate),
                    enabled = betDetail.bet.betStatus == BetStatus.IN_PROGRESS,
                    onClick = openParticipateSheet
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun DisplayYesNoBet(betDetail: BetDetail) {
        DisplayBinaryBet(
            betDetail = betDetail,
            response1 = YES_VALUE,
            response2 = NO_VALUE,
            response1Display = stringResource(id = R.string.Yes).uppercase(),
            response2Display = stringResource(id = R.string.No).uppercase()
        )
    }

    @Composable
    override fun DisplayMatchBet(betDetail: BetDetail) {
        val bet = remember { betDetail.bet as MatchBet }
        DisplayBinaryBet(
            betDetail = betDetail,
            response1 = bet.nameTeam1,
            response2 = bet.nameTeam2
        )
    }

    @Composable
    override fun DisplayCustomBet(betDetail: BetDetail) {
        Text("This is a CUSTOM BET")
    }
}

@Composable
fun BetStatusParticipant(
    username: String,
    allCoinsAmount: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfilePicture(modifier = Modifier.size(25.dp))
        Text(
            text = username,
            fontWeight = FontWeight.Bold,
            style = AllInTheme.typography.h2,
            color = AllInTheme.themeColors.onMainSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        AllInCoinCount(
            amount = allCoinsAmount,
            color = AllInTheme.colors.allInPurple
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetStatusBottomSheetPreview(
    @PreviewParameter(BetDetailPreviewProvider::class) bet: BetDetail
) {
    AllInTheme {
        BetStatusBottomSheetBetDisplayer {

        }.DisplayBet(bet)
    }
}