package fr.iut.alldev.allin.ext

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.ui.graphics.vector.ImageVector
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.bet.BetType

@StringRes
fun BetType.getTitleId(): Int {
    return when (this) {
        BetType.YES_NO -> R.string.yes_no
        BetType.MATCH -> R.string.sport_match
        BetType.CUSTOM -> R.string.custom_answers
    }
}

fun BetType.getIcon(): ImageVector {
    return when (this) {
        BetType.YES_NO -> Icons.AutoMirrored.Default.HelpOutline
        BetType.MATCH -> Icons.Default.SportsSoccer
        BetType.CUSTOM -> Icons.Default.Edit
    }
}