package fr.iut.alldev.allin.ext

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.bet.BetFinishedStatus.LOST
import fr.iut.alldev.allin.data.model.bet.BetFinishedStatus.WON
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.theme.AllInTheme

@StringRes
fun BetStatus.getTitleId(): Int {
    return when (this) {
        is BetStatus.Finished -> R.string.bet_status_finished
        BetStatus.InProgress -> R.string.bet_status_in_progress
        BetStatus.Waiting -> R.string.bet_status_waiting
    }
}

@StringRes
fun BetStatus.getDateStartLabelId(): Int {
    return when (this) {
        is BetStatus.Finished -> R.string.Started
        else -> R.string.Starting
    }
}

@StringRes
fun BetStatus.getDateEndLabelId(): Int {
    return when (this) {
        is BetStatus.Finished -> R.string.Ended
        else -> R.string.Ends
    }
}

@Composable
fun BetStatus.getColor(): Color {
    return when (this) {
        is BetStatus.Finished -> AllInTheme.colors.allInBetFinish
        BetStatus.InProgress -> AllInTheme.colors.allInBetInProgress
        BetStatus.Waiting -> AllInTheme.colors.allInBetWaiting
    }
}

@Composable
fun BetStatus.getTextColor(): Color {
    return when (this) {
        is BetStatus.Finished -> AllInTheme.colors.allInBetFinishText
        BetStatus.InProgress -> AllInTheme.colors.allInBetInProgressText
        BetStatus.Waiting -> AllInTheme.colors.allInBetWaitingText
    }
}

@StringRes
fun BetStatus.getBetHistoryPhrase(): Int {
    return when (this) {
        is BetStatus.Finished -> when (this.status) {
            WON -> R.string.bet_history_status_won
            LOST -> R.string.bet_history_status_lost
        }

        else -> R.string.bet_history_status_in_progress
    }
}

@Composable
fun BetStatus.getBetHistoryStatusColor(): Brush {
    return when (this) {
        is BetStatus.Finished -> when (this.status) {
            WON -> AllInTheme.colors.allInMainGradient
            LOST -> AllInTheme.colors.allInDarkGradient
        }

        else -> SolidColor(AllInTheme.colors.allInDarkGrey100)
    }
}