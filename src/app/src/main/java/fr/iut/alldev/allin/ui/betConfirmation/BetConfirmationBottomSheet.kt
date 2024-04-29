package fr.iut.alldev.allin.ui.betConfirmation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.ext.formatToMediumDateNoYear
import fr.iut.alldev.allin.data.ext.formatToTime
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.NO_VALUE
import fr.iut.alldev.allin.data.model.bet.YES_VALUE
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.data.model.bet.vo.BetAnswerDetail
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import fr.iut.alldev.allin.ext.formatToSimple
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInBottomSheet
import fr.iut.alldev.allin.ui.core.AllInButton
import fr.iut.alldev.allin.ui.core.AllInCard
import fr.iut.alldev.allin.ui.core.AllInMarqueeBox
import fr.iut.alldev.allin.ui.core.bet.BetCard
import java.time.ZonedDateTime
import java.util.Locale

@Composable
fun BetConfirmationBottomSheet(
    state: SheetState,
    sheetVisibility: Boolean,
    betDetail: BetDetail,
    onDismiss: () -> Unit,
    onConfirm: (selectedAnswer: String) -> Unit
) {
    AllInBottomSheet(
        sheetVisibility = sheetVisibility,
        onDismiss = onDismiss,
        state = state,
        dragHandle = null
    ) {
        BetConfirmationBottomSheetContent(
            betDetail = betDetail,
            onConfirm = {
                onConfirm(it)
                onDismiss()
            },
            onClose = onDismiss
        )
    }
}

@Composable
fun BetConfirmationBottomSheetAnswer(
    text: String,
    odds: Float,
    modifier: Modifier = Modifier,
    color: Color = AllInColorToken.allInBlue,
    isSelected: Boolean,
    locale: Locale,
    onClick: () -> Unit
) {
    val backColor = if (isSelected) AllInColorToken.allInPurple else AllInColorToken.white
    val contentColor = if (isSelected) AllInColorToken.white else null

    AllInCard(
        backgroundColor = backColor,
        onClick = onClick,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 18.dp),
        ) {
            Text(
                text = text.uppercase(),
                color = contentColor ?: color,
                style = AllInTheme.typography.h1,
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 64.dp)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clip(RoundedCornerShape(50.dp))
                    .background(contentColor ?: AllInColorToken.allInPurple)
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = "x${odds.formatToSimple(locale)}",
                    color = backColor,
                    style = AllInTheme.typography.h2
                )
            }
        }
    }
}

@Composable
fun ConfirmationAnswers(
    betDetail: BetDetail,
    selectedAnswer: String?,
    onClick: (String) -> Unit
) {
    val configuration = LocalConfiguration.current
    val locale = remember { ConfigurationCompat.getLocales(configuration).get(0) ?: Locale.getDefault() }

    val possibleAnswers = remember {
        when (val bet = betDetail.bet) {
            is CustomBet -> bet.possibleAnswers
            is MatchBet -> listOf(bet.nameTeam1, bet.nameTeam2)
            is YesNoBet -> listOf(YES_VALUE, NO_VALUE)
        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(possibleAnswers) { idx, it ->
            betDetail.getAnswerOfResponse(it)?.let {
                val opacity by animateFloatAsState(
                    targetValue = if (selectedAnswer != null && selectedAnswer != it.response) .5f else 1f,
                    label = ""
                )

                BetConfirmationBottomSheetAnswer(
                    text = it.response,
                    odds = it.odds,
                    locale = locale,
                    color = if (possibleAnswers.size == 2 && idx == 1) {
                        AllInColorToken.allInBarPink
                    } else {
                        AllInColorToken.allInBlue
                    },
                    onClick = { onClick(it.response) },
                    isSelected = selectedAnswer == it.response,
                    modifier = Modifier.alpha(opacity)
                )
            }
        }
    }
}

@Composable
fun BetConfirmationBottomSheetContent(
    betDetail: BetDetail,
    onConfirm: (String) -> Unit,
    onClose: () -> Unit
) {
    var selectedAnswer by remember { mutableStateOf<String?>(null) }

    AllInMarqueeBox(backgroundColor = AllInColorToken.allInDarkGrey300) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
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
                    tint = AllInColorToken.white,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }

            Icon(
                painter = AllInTheme.icons.logo(),
                contentDescription = null,
                tint = AllInColorToken.white,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopCenter)
            )

            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BetCard(
                    title = betDetail.bet.phrase,
                    creator = betDetail.bet.creator,
                    category = betDetail.bet.theme,
                    date = betDetail.bet.endBetDate.formatToMediumDateNoYear(),
                    time = betDetail.bet.endBetDate.formatToTime(),
                    status = betDetail.bet.betStatus
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(AllInColorToken.allInMainGradient)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.Finished),
                            color = AllInColorToken.white,
                            style = AllInTheme.typography.h1,
                            fontSize = 24.sp
                        )
                    }
                }
                Text(
                    text = stringResource(id = R.string.bet_confirmation_text),
                    color = AllInColorToken.allInLightGrey200,
                    style = AllInTheme.typography.p2,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.bet_confirmation_choose_response),
                    fontSize = 18.sp,
                    color = AllInColorToken.white,
                    style = AllInTheme.typography.h1,
                    modifier = Modifier.fillMaxWidth()
                )
                ConfirmationAnswers(
                    betDetail = betDetail,
                    selectedAnswer = selectedAnswer
                ) { selectedAnswer = if (selectedAnswer == it) null else it }
            }
            if (selectedAnswer != null) {
                AllInButton(
                    color = AllInColorToken.allInPurple,
                    text = stringResource(id = R.string.Validate),
                    textColor = AllInColorToken.white,
                    radius = 5.dp,
                    onClick = { selectedAnswer?.let(onConfirm) },
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Preview
@Composable
private fun BetConfirmationBottomSheetContentPreview() {
    AllInTheme {
        BetConfirmationBottomSheetContent(
            betDetail = BetDetail(
                bet = YesNoBet(
                    id = "1",
                    theme = "Theme",
                    phrase = "Phrase",
                    endRegisterDate = ZonedDateTime.now(),
                    endBetDate = ZonedDateTime.now(),
                    isPublic = true,
                    betStatus = BetStatus.FINISHED,
                    creator = "creator"
                ),
                answers = listOf(
                    BetAnswerDetail(
                        response = YES_VALUE,
                        totalStakes = 300,
                        totalParticipants = 2,
                        highestStake = 200,
                        odds = 1.0f
                    ),
                    BetAnswerDetail(
                        response = NO_VALUE,
                        totalStakes = 150,
                        totalParticipants = 1,
                        highestStake = 150,
                        odds = 2.255f
                    )
                ),
                participations = emptyList(),
                userParticipation = null,
                wonParticipation = null
            ),
            onConfirm = { }
        ) {

        }
    }
}