package fr.iut.alldev.allin.ui.betStatus

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.ui.betStatus.components.BetStatusBottomSheetBack
import fr.iut.alldev.allin.ui.betStatus.components.BetStatusParticipationBottomSheet
import fr.iut.alldev.allin.ui.betStatus.components.getParticipationAnswers
import fr.iut.alldev.allin.ui.core.AllInBottomSheet


internal const val SHEET_HEIGHT = .85f


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetStatusBottomSheet(
    state: SheetState,
    sheetVisibility: Boolean,
    sheetBackVisibility: Boolean,
    bet: Bet?,
    paddingValues: PaddingValues,
    userCoinAmount: MutableIntState,
    onParticipate: (Int) -> Unit,
    onDismiss: () -> Unit,
    participateSheetVisibility: Boolean,
    setParticipateSheetVisibility: (Boolean) -> Unit,
    displayBet: @Composable (Bet) -> Unit
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
        bet?.let {
            BetStatusBottomSheetBack(
                status = it.betStatus
            )
        }
    }

    AllInBottomSheet(
        sheetVisibility = sheetVisibility,
        onDismiss = onDismiss,
        state = state,
        scrimColor = Color.Transparent
    ) {

        var selectedAnswer by remember { mutableStateOf(0) }
        var stake by remember { mutableStateOf<Int?>(null) }

        Column(
            Modifier.fillMaxHeight(SHEET_HEIGHT)
        ) {
            bet?.let {
                val elements = bet.getParticipationAnswers()

                displayBet(it)
                BetStatusParticipationBottomSheet(
                    sheetVisibility = participateSheetVisibility && bet.betStatus == BetStatus.Waiting && state.hasExpandedState,
                    safeBottomPadding = paddingValues.calculateBottomPadding(),
                    betPhrase = bet.phrase,
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
                    stake?.let {
                        onParticipate(it)
                    }
                }

            }
        }

    }

}
