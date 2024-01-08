package fr.iut.alldev.allin.ui.betHistory.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.ext.getBetHistoryPhrase
import fr.iut.alldev.allin.ext.getBetHistoryStatusColor
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.preview.BetStatusPreviewProvider

const val betHistoryStatusModId = "allCoinsIcon"
val betHistoryStatusInlineContent = mapOf(
    Pair(
        betHistoryStatusModId,
        InlineTextContent(
            Placeholder(
                width = 24.sp,
                height = 24.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(
                painter = AllInTheme.icons.allCoins(),
                tint = AllInTheme.colors.white,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    )
)

@Composable
fun BetHistoryBetStatus(
    status: BetStatus,
    nbCoins: Int,
) {
    val betHistoryPhrase = stringResource(id = status.getBetHistoryPhrase(), nbCoins)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(status.getBetHistoryStatusColor())
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                append(betHistoryPhrase.substringBefore("[icon]"))
                appendInlineContent(betHistoryStatusModId, "[icon]")
                append(betHistoryPhrase.substringAfter("[icon]"))
            },
            color = AllInTheme.colors.white,
            inlineContent = betHistoryStatusInlineContent,
            style = AllInTheme.typography.h1,
            fontSize = 24.sp
        )
    }
}

@Preview
@Composable
private fun BetHistoryBetStatusPreview(
    @PreviewParameter(BetStatusPreviewProvider::class) betStatus: BetStatus,
) {
    AllInTheme {
        BetHistoryBetStatus(
            status = betStatus,
            nbCoins = 230
        )
    }
}