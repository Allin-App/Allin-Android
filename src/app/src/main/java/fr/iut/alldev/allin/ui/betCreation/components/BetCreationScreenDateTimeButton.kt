package fr.iut.alldev.allin.ui.betCreation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInCard

@Composable
fun BetCreationScreenDateTimeButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    AllInCard(
        modifier = modifier,
        radius = 10.dp,
        onClick = onClick
    ) {
        Text(
            text = text,
            color = AllInColorToken.allInPurple,
            style = AllInTheme.typography.h2,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 23.dp, vertical = 9.dp)
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetCreationScreenDateTimeButtonPreview() {
    AllInTheme {
        BetCreationScreenDateTimeButton(
            text = "13:00",
            onClick = {}
        )
    }
}