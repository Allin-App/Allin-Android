package fr.iut.alldev.allin.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.ui.theme.AllInTheme

fun BetStatus.getTitle(): Int {
    return when (this) {
        BetStatus.FINISHED -> R.string.bet_status_finished
        BetStatus.IN_PROGRESS -> R.string.bet_status_in_progress
        BetStatus.WAITING -> R.string.bet_status_waiting
    }
}

@Composable
fun BetStatus.getColor(): Color {
    return when (this) {
        BetStatus.FINISHED -> AllInTheme.colors.allIn_BetFinish
        BetStatus.IN_PROGRESS -> AllInTheme.colors.allIn_BetInProgress
        BetStatus.WAITING -> AllInTheme.colors.allIn_BetWaiting
    }
}

@Composable
fun BetStatus.getTextColor(): Color {
    return when (this) {
        BetStatus.FINISHED -> AllInTheme.colors.allIn_BetFinishText
        BetStatus.IN_PROGRESS -> AllInTheme.colors.allIn_BetInProgressText
        BetStatus.WAITING -> AllInTheme.colors.allIn_BetWaitingText
    }
}