package fr.iut.alldev.allin.ui.core.bet

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.ext.getDateStartLabelId
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInBouncyCard
import fr.iut.alldev.allin.ui.core.AllInCard

@Composable
fun BetCard(
    modifier: Modifier = Modifier,
    title: String,
    creator: String,
    category: String,
    date: String,
    time: String,
    status: BetStatus,
    content: @Composable () -> Unit
) {
    AllInCard(
        modifier = modifier.fillMaxWidth(),
        radius = 16.dp
    ) {
        Column(
            Modifier.padding(horizontal = 19.dp, vertical = 11.dp)
        ) {
            BetTitleHeader(
                title = title,
                category = category,
                creator = creator,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(11.dp))
            BetDateTimeRow(
                label = stringResource(id = status.getDateStartLabelId()),
                date = date,
                time = time
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = AllInTheme.colors.border
        )
        content()
    }
}

@Composable
fun BetCard(
    modifier: Modifier = Modifier,
    title: String,
    creator: String,
    category: String,
    date: String,
    time: String,
    status: BetStatus,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    AllInBouncyCard(
        modifier = modifier.fillMaxWidth(),
        radius = 16.dp,
        onClick = onClick
    ) {
        Column(
            Modifier.padding(horizontal = 19.dp, vertical = 11.dp)
        ) {
            BetTitleHeader(
                title = title,
                category = category,
                creator = creator,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(11.dp))
            BetDateTimeRow(
                label = stringResource(id = status.getDateStartLabelId()),
                date = date,
                time = time
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = AllInTheme.colors.border
        )
        content()
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetCardPreview() {
    AllInTheme {
        BetCard(
            creator = "Creator",
            category = "Category",
            title = "Title",
            date = "Date",
            time = "Time",
            status = BetStatus.WAITING
        ) {
            Text("Content")
        }
    }
}