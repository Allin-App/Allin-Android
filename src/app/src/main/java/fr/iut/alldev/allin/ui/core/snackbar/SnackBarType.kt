package fr.iut.alldev.allin.ui.core.snackbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.snackbar.SnackbarType.ERROR
import fr.iut.alldev.allin.ui.core.snackbar.SnackbarType.STANDARD
import fr.iut.alldev.allin.ui.core.snackbar.SnackbarType.SUCCESS

enum class SnackbarType {
    STANDARD,
    SUCCESS,
    ERROR
}

@Composable
fun SnackbarType.getBackgroundColor(): Color =
    when (this) {
        STANDARD -> AllInTheme.colors.allInDark
        SUCCESS -> AllInTheme.colors.allInPurple
        ERROR -> AllInTheme.colors.allInBetWaiting
    }

@Composable
fun SnackbarType.getIcon(): ImageVector =
    when (this) {
        STANDARD -> Icons.Default.Info
        ERROR -> Icons.Default.Error
        SUCCESS -> Icons.Default.CheckCircle
    }