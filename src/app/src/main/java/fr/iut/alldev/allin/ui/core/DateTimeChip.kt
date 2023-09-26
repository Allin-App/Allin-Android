package fr.iut.alldev.allin.ui.bet.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun DateTimeChip(
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.wrapContentSize(),
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, AllInTheme.themeColors.border),
        colors = CardDefaults.cardColors(containerColor = AllInTheme.themeColors.background)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            style = AllInTheme.typography.h3,
            textAlign = TextAlign.Center,
            color = AllInTheme.colors.allIn_Purple
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeBetCardDateTimeChipPreview() {
    AllInTheme {
        DateTimeChip("11 Sept.")
    }
}