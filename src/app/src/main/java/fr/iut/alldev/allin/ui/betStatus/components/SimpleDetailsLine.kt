package fr.iut.alldev.allin.ui.betStatus.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInTextIcon
import fr.iut.alldev.allin.ui.core.IconPosition

@Composable
fun SimpleDetailsLine(
    icon: Painter,
    text: String,
    isWin: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        AllInTextIcon(
            text = text,
            color = if (isWin) {
                AllInTheme.colors.allInBarViolet
            } else {
                AllInTheme.colors.allInBlue
            },
            icon = icon,
            position = IconPosition.TRAILING,
            size = 15,
            iconSize = 15
        )
    }
}

@Preview
@Composable
private fun SimpleDetailsLineWinPreview() {
    AllInTheme {
        SimpleDetailsLine(
            icon = AllInTheme.icons.allCoins(),
            text = "550",
            isWin = true
        )
    }
}

@Preview
@Composable
private fun SimpleDetailsLinePreview() {
    AllInTheme {
        SimpleDetailsLine(
            icon = AllInTheme.icons.allCoins(),
            text = "550",
            isWin = false
        )
    }
}