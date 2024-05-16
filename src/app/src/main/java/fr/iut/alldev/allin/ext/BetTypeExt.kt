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
        BetType.BINARY -> R.string.bet_type_binary
        BetType.MATCH -> R.string.bet_type_match
        BetType.CUSTOM -> R.string.bet_type_custom
    }
}

fun BetType.getIcon(): ImageVector {
    return when (this) {
        BetType.BINARY -> Icons.AutoMirrored.Default.HelpOutline
        BetType.MATCH -> Icons.Default.SportsSoccer
        BetType.CUSTOM -> Icons.Default.Edit
    }
}