package fr.iut.alldev.allin.ext

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.theme.AllInTheme

@StringRes
fun BetStatus.getTitleId(): Int {
    return when (this) {
        BetStatus.IN_PROGRESS -> R.string.bet_status_in_progress
        BetStatus.WAITING -> R.string.bet_status_waiting
        BetStatus.CLOSING -> R.string.bet_status_closing
        BetStatus.FINISHED -> R.string.bet_status_finished
        BetStatus.CANCELLED -> R.string.bet_status_cancelled
    }
}

@StringRes
fun BetStatus.getDateStartLabelId(): Int {
    return when (this) {
        BetStatus.CLOSING, BetStatus.FINISHED, BetStatus.CANCELLED -> R.string.Started
        else -> R.string.Starting
    }
}

@StringRes
fun BetStatus.getDateEndLabelId(): Int {
    return when (this) {
        BetStatus.CLOSING, BetStatus.FINISHED, BetStatus.CANCELLED -> R.string.Ended
        else -> R.string.Ends
    }
}

@Composable
fun BetStatus.getColor(): Color {
    return when (this) {
        BetStatus.FINISHED -> AllInTheme.colors.allInBetFinish
        BetStatus.IN_PROGRESS -> AllInTheme.colors.allInBetInProgress
        BetStatus.WAITING -> AllInTheme.colors.allInBetWaiting
        else -> AllInTheme.colors.allInBetFinish // TODO
    }
}

@Composable
fun BetStatus.getTextColor(): Color {
    return when (this) {
        BetStatus.FINISHED -> AllInTheme.colors.allInBetFinishText
        BetStatus.IN_PROGRESS -> AllInTheme.colors.allInBetInProgressText
        BetStatus.WAITING -> AllInTheme.colors.allInBetWaitingText
        else -> AllInTheme.colors.allInBetFinishText // TODO
    }
}

@StringRes
fun BetStatus.getBetHistoryPhrase(won: Boolean): Int {
    return when (this) {
        BetStatus.FINISHED ->
            if (won) R.string.bet_history_status_won else R.string.bet_history_status_lost

        else -> R.string.bet_history_status_in_progress
    }
}

@Composable
fun BetStatus.getBetHistoryStatusColor(won: Boolean): Brush {
    return when (this) {
        BetStatus.FINISHED ->
            if (won) AllInTheme.colors.allInMainGradient else AllInTheme.colors.allInDarkGradient

        else -> SolidColor(AllInTheme.colors.allInDarkGrey100)
    }
}