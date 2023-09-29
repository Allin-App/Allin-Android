package fr.iut.alldev.allin.ext

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.BetType

@Composable
fun BetType.getTitle(): String {
    return when (this) {
        BetType.YES_NO -> stringResource(id = R.string.yes_no)
        BetType.MATCH -> stringResource(id = R.string.sport_match)
        BetType.CUSTOM -> stringResource(id = R.string.custom_answers)
    }
}

@Composable
fun BetType.getIcon(): ImageVector {
    return when (this) {
        BetType.YES_NO -> Icons.AutoMirrored.Default.HelpOutline
        BetType.MATCH -> Icons.Default.SportsSoccer
        BetType.CUSTOM -> Icons.Default.Edit
    }
}