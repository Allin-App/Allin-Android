package fr.iut.alldev.allin.ui.betcreation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun BetCreationScreenDateTimeRow(
    modifier: Modifier = Modifier,
    date: String,
    time: String,
    onClickDate: ()->Unit,
    onClickTime: ()->Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        BetCreationScreenDateTimeButton(text = date, onClick = onClickDate)
        BetCreationScreenDateTimeButton(text = time, onClick = onClickTime)
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetCreationScreenDateTimeRowPreview() {
    AllInTheme {
        BetCreationScreenDateTimeRow(
            date = "Sept. 11, 2023",
            time = "13:00",
            onClickDate = {},
            onClickTime = {}
        )
    }
}