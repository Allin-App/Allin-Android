package fr.iut.alldev.allin.ui.betStatus.vo

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.NO_VALUE
import fr.iut.alldev.allin.data.model.bet.YES_VALUE
import fr.iut.alldev.allin.data.model.bet.vo.BetAnswerDetail
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import fr.iut.alldev.allin.ext.asFallbackProfileUsername
import fr.iut.alldev.allin.ext.asPaddingValues
import fr.iut.alldev.allin.ext.bottomSheetNavigationBarsInsets
import fr.iut.alldev.allin.ext.formatToSimple
import fr.iut.alldev.allin.ext.getDateEndLabelId
import fr.iut.alldev.allin.ext.getDateStartLabelId
import fr.iut.alldev.allin.ext.nonLinkedScroll
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.betStatus.components.BetStatusWinner
import fr.iut.alldev.allin.ui.betStatus.components.BinaryDetailsLine
import fr.iut.alldev.allin.ui.betStatus.components.BinaryStatBar
import fr.iut.alldev.allin.ui.betStatus.components.SimpleDetailsLine
import fr.iut.alldev.allin.ui.betStatus.components.SimpleStatBar
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
    @Composable
    private fun DisplayBetDail(
        betDetail: BetDetail,
        currentUser: User,
        winnerColor: @Composable () -> Color,
        statBar: LazyListScope.() -> Unit
    ) {
        Box(Modifier) {
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
                    betDetail.wonParticipation?.let { wonParticipation ->
                        BetStatusWinner(
                            answer = wonParticipation.response,
                            color = winnerColor(),
                            coinAmount = wonParticipation.stake,
                            username = wonParticipation.username,
                            multiplier = betDetail.getAnswerOfResponse(wonParticipation.response)
                                ?.odds ?: .5f
                        )
                    }
                } else {
                    HorizontalDivider(color = AllInTheme.colors.border)
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(AllInTheme.colors.background2)
                        .nonLinkedScroll(),
                    contentPadding = bottomSheetNavigationBarsInsets().asPaddingValues(top = 20.dp, start = 20.dp, end = 20.dp)
                ) {

                    statBar(this)

                    if (betDetail.participations.isNotEmpty() || betDetail.userParticipation != null) {
                        item {
                            Text(
                                text = stringResource(id = R.string.bet_status_participants_list),
                                fontSize = 20.sp,
                                color = AllInTheme.colors.onMainSurface,
                                style = AllInTheme.typography.h1,
                                modifier = Modifier.padding(vertical = 36.dp)
                            )
                        }
                    }

                    betDetail.userParticipation?.let {
                        item {
                            BetStatusParticipant(
                                username = it.username,
                                allCoinsAmount = it.stake,
                                image = null // TODO : Image
                            )
                            HorizontalDivider(
                                color = AllInTheme.colors.border,
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 25.dp)
                            )
                        }
                    }
                    items(betDetail.participations) {
                        if (it.username != betDetail.userParticipation?.username) {
                            BetStatusParticipant(
                                username = it.username,
                                allCoinsAmount = it.stake,
                                image = null // TODO : Image
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                    item {
                        if (betDetail.bet.betStatus != BetStatus.FINISHED && betDetail.userParticipation == null) {
                            Spacer(modifier = Modifier.height(75.dp))
                        }
                    }
                }
            }
            if (
                betDetail.bet.betStatus != BetStatus.FINISHED &&
                betDetail.userParticipation == null &&
                betDetail.bet.creator != currentUser.username
            ) {
                RainbowButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                .2f to Color.Transparent,
                                .5f to AllInTheme.colors.background2
                            )
                        )
                        .padding(bottomSheetNavigationBarsInsets().asPaddingValues(7.dp)),
                    text = stringResource(id = R.string.bet_participate),
                    enabled = betDetail.bet.betStatus == BetStatus.IN_PROGRESS,
                    onClick = openParticipateSheet
                )
            }
        }
    }

    private fun LazyListScope.displayBinaryStatBar(
        betDetail: BetDetail,
        response1: String,
        response2: String,
        response1Display: @Composable () -> String = { response1 },
        response2Display: @Composable () -> String = { response2 }
    ) {
        item {
            val configuration = LocalConfiguration.current
            val locale = remember { ConfigurationCompat.getLocales(configuration).get(0) ?: Locale.getDefault() }

            val response1Answer = remember(betDetail) { betDetail.getAnswerOfResponse(response1) }
            val response2Answer = remember(betDetail) { betDetail.getAnswerOfResponse(response2) }

            BinaryStatBar(
                response1Percentage = remember(betDetail) {
                    response1Answer?.let { betDetail.getPercentageOfAnswer(response1Answer) } ?: 0f
                },
                response1 = response1Display(),
                response2 = response2Display()
            )
            AllInDetailsDrawer {
                BinaryDetailsLine(
                    icon = AllInTheme.icons.allCoins(),
                    yesText = response1Answer?.totalStakes?.toString() ?: "0",
                    noText = response2Answer?.totalStakes?.toString() ?: "0"
                )
                BinaryDetailsLine(
                    icon = rememberVectorPainter(image = Icons.Filled.People),
                    yesText = response1Answer?.totalParticipants?.toString() ?: "0",
                    noText = response2Answer?.totalParticipants?.toString() ?: "0"
                )
                BinaryDetailsLine(
                    icon = rememberVectorPainter(image = Icons.Filled.WorkspacePremium),
                    yesText = response1Answer?.highestStake?.toString() ?: "0",
                    noText = response2Answer?.highestStake?.toString() ?: "0"
                )
                BinaryDetailsLine(
                    icon = rememberVectorPainter(image = Icons.Filled.EmojiEvents),
                    yesText = "x${response1Answer?.odds?.formatToSimple(locale) ?: "1.00"}",
                    noText = "x${response2Answer?.odds?.formatToSimple(locale) ?: "1.00"}"
                )
            }
        }
    }

    private fun LazyListScope.displayMultiStatBar(
        responsesWithDetail: List<Pair<BetAnswerDetail, Float>>,
        locale: Locale
    ) {
        itemsIndexed(responsesWithDetail) { idx, (answer, percentage) ->
            val isWin = remember { idx == 0 }

            SimpleStatBar(
                percentage = percentage,
                response = answer.response,
                isWin = isWin
            )

            AllInDetailsDrawer {
                SimpleDetailsLine(
                    icon = AllInTheme.icons.allCoins(),
                    text = answer.totalStakes.toString(),
                    isWin = isWin
                )
                SimpleDetailsLine(
                    icon = rememberVectorPainter(image = Icons.Filled.People),
                    text = answer.totalParticipants.toString(),
                    isWin = isWin
                )
                SimpleDetailsLine(
                    icon = rememberVectorPainter(image = Icons.Filled.WorkspacePremium),
                    text = answer.highestStake.toString(),
                    isWin = isWin
                )
                SimpleDetailsLine(
                    icon = rememberVectorPainter(image = Icons.Filled.EmojiEvents),
                    text = "x${answer.odds.formatToSimple(locale)}",
                    isWin = isWin
                )
            }
        }
    }

    @Composable
    override fun DisplayBinaryBet(betDetail: BetDetail, currentUser: User) {
        DisplayBetDail(
            betDetail = betDetail,
            currentUser = currentUser,
            winnerColor = {
                if (betDetail.wonParticipation?.response == YES_VALUE) AllInColorToken.allInBlue
                else AllInColorToken.allInPink
            }
        ) {
            displayBinaryStatBar(
                betDetail = betDetail,
                response1 = YES_VALUE,
                response2 = NO_VALUE,
                response1Display = { stringResource(id = R.string.generic_yes).uppercase() },
                response2Display = { stringResource(id = R.string.generic_no).uppercase() },
            )
        }
    }

    @Composable
    override fun DisplayMatchBet(betDetail: BetDetail, currentUser: User) {
        val matchBet = remember { betDetail.bet as MatchBet }

        DisplayBetDail(
            betDetail = betDetail,
            currentUser = currentUser,
            winnerColor = {
                if (betDetail.wonParticipation?.response == matchBet.nameTeam1) AllInColorToken.allInBlue
                else AllInColorToken.allInPink
            }
        ) {
            displayBinaryStatBar(
                betDetail = betDetail,
                response1 = matchBet.nameTeam1,
                response2 = matchBet.nameTeam2
            )
        }
    }

    @Composable
    override fun DisplayCustomBet(betDetail: BetDetail, currentUser: User) {
        val customBet = remember { betDetail.bet as CustomBet }
        val configuration = LocalConfiguration.current
        val locale = remember { ConfigurationCompat.getLocales(configuration).get(0) ?: Locale.getDefault() }

        val responsesWithDetail = remember(betDetail) {
            if (customBet.possibleAnswers.size > 2) {
                customBet.getResponses().mapNotNull {
                    betDetail.getAnswerOfResponse(it)
                }.associateWith {
                    betDetail.getPercentageOfAnswer(it)
                }
                    .toList()
                    .sortedByDescending { it.second }
            } else emptyList()
        }

        DisplayBetDail(
            betDetail = betDetail,
            currentUser = currentUser,
            winnerColor = {
                val isBinary = remember { customBet.possibleAnswers.size == 2 }

                if (isBinary) {
                    if (betDetail.wonParticipation?.response == customBet.possibleAnswers.first()) AllInColorToken.allInBlue
                    else AllInColorToken.allInPink
                } else AllInTheme.colors.onMainSurface
            }
        ) {
            if (customBet.possibleAnswers.size == 2) {
                displayBinaryStatBar(
                    betDetail = betDetail,
                    response1 = customBet.possibleAnswers.first(),
                    response2 = customBet.possibleAnswers.last()
                )
            } else {
                displayMultiStatBar(
                    responsesWithDetail = responsesWithDetail,
                    locale = locale
                )
            }
        }
    }
}

@Composable
fun BetStatusParticipant(
    username: String,
    image: String?,
    allCoinsAmount: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfilePicture(
            image = image,
            fallback = username.asFallbackProfileUsername(),
            size = 25.dp
        )
        Text(
            text = username,
            fontWeight = FontWeight.Bold,
            style = AllInTheme.typography.h2,
            color = AllInTheme.colors.onMainSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        AllInCoinCount(
            amount = allCoinsAmount,
            color = AllInColorToken.allInPurple
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
        BetStatusBottomSheetBetDisplayer(
            openParticipateSheet = {}
        ).DisplayBet(
            betDetail = bet,
            currentUser = User(
                id = "x",
                username = "aaa",
                email = "aaa",
                coins = 150,
                nbBets = 0,
                nbFriends = 0,
                bestWin = 0,
            )
        )
    }
}