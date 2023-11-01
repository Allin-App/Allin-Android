package fr.iut.alldev.allin.ui.betstatus.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.core.AllInCoinCount
import fr.iut.alldev.allin.ui.core.AllInTextIcon
import fr.iut.alldev.allin.ui.core.IconPosition
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun BetStatusWinner(
    answer: String,
    coinAmount: Int,
    username: String,
    multiplier: Float,
    color: Color = AllInTheme.themeColors.on_main_surface
) {
    Column {
        HorizontalDivider(color = color.copy(alpha = .4f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(color.copy(alpha = .2f))
                .padding(vertical = 20.dp)
        ) {
            AllInTextIcon(
                text = answer,
                icon = Icons.Filled.EmojiEvents,
                color = color,
                size = 50,
                iconSize = 60,
                position = IconPosition.LEADING
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                AllInCoinCount(
                    amount = coinAmount,
                    color = color,
                    position = IconPosition.LEADING
                )
                AllInTextIcon(
                    text = username,
                    icon = Icons.Filled.People,
                    color = color,
                    position = IconPosition.LEADING
                )
                AllInTextIcon(
                    text = "x" + String.format("%.1f", multiplier),
                    icon = Icons.Filled.EmojiEvents,
                    color = color,
                    position = IconPosition.LEADING
                )
            }
        }
    }
    HorizontalDivider(color = color.copy(alpha = .4f))
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetStatusWinnerPreview() {
    AllInTheme {
        BetStatusWinner(
            answer = "Answer",
            coinAmount = 435,
            username = "Imri",
            multiplier = 1.2f,
        )
    }
}