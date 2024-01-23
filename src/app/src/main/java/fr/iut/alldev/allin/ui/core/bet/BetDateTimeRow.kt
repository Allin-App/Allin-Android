package fr.iut.alldev.allin.ui.core.bet

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun BetDateTimeRow(
    label: String,
    date: String,
    time: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Text(
            text = label,
            fontSize = 15.sp,
            style = AllInTheme.typography.sm2,
            color = AllInTheme.themeColors.onBackground2
        )
        BetDateTimeChip(date)
        BetDateTimeChip(time)
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetDateTimeRowPreview() {
    AllInTheme {
        BetDateTimeRow(label = "Commence le", date = "11 Sept.", time = "13:00")
    }
}