package fr.iut.alldev.allin.ui.betStatus

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import fr.iut.alldev.allin.ui.betStatus.components.BetStatusBottomSheetBack
import fr.iut.alldev.allin.ui.betStatus.components.BetStatusParticipationBottomSheet
import fr.iut.alldev.allin.ui.betStatus.components.getAnswerFromParticipationIdx
import fr.iut.alldev.allin.ui.betStatus.components.getParticipationAnswers
import fr.iut.alldev.allin.ui.core.AllInBottomSheet


internal const val SHEET_HEIGHT = .85f


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetStatusBottomSheet(
    state: SheetState,
    sheetVisibility: Boolean,
    sheetBackVisibility: Boolean,
    betDetail: BetDetail?,
    userCoinAmount: MutableIntState,
    onParticipate: (stake: Int, response: String) -> Unit,
    onDismiss: () -> Unit,
    participateSheetVisibility: Boolean,
    setParticipateSheetVisibility: (Boolean) -> Unit,
    displayBet: @Composable (BetDetail) -> Unit
) {
    AnimatedVisibility(
        visible = sheetBackVisibility,
        enter = slideInVertically(
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            targetOffsetY = { it }
        )
    ) {
        betDetail?.let {
            BetStatusBottomSheetBack(
                status = it.bet.betStatus
            )
        }
    }

    AllInBottomSheet(
        sheetVisibility = sheetVisibility,
        onDismiss = onDismiss,
        state = state,
        scrimColor = Color.Transparent
    ) {

        var selectedAnswer by remember { mutableIntStateOf(0) }
        var stake by remember { mutableStateOf<Int?>(null) }

        Column(
            Modifier.fillMaxHeight(SHEET_HEIGHT)
        ) {
            betDetail?.let {
                val elements = betDetail.getParticipationAnswers()

                displayBet(it)
                BetStatusParticipationBottomSheet(
                    sheetVisibility = participateSheetVisibility &&
                            betDetail.bet.betStatus == BetStatus.IN_PROGRESS &&
                            state.hasExpandedState,
                    odds = betDetail.answers.getOrNull(selectedAnswer)?.odds ?: 1f,
                    betPhrase = betDetail.bet.phrase,
                    coinAmount = userCoinAmount.intValue,
                    onDismiss = { setParticipateSheetVisibility(false) },
                    state = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                    elements = elements,
                    selectedElement = elements.getOrNull(selectedAnswer),
                    stake = stake,
                    setStake = { stake = it },
                    setElement = { idx -> selectedAnswer = idx },
                    enabled = stake != null &&
                            (stake ?: 0) <= userCoinAmount.intValue
                ) {
                    stake?.let { stake ->
                        onParticipate(
                            stake,
                            betDetail.bet.getAnswerFromParticipationIdx(selectedAnswer)
                        )
                    }
                }

            }
        }

    }

}
