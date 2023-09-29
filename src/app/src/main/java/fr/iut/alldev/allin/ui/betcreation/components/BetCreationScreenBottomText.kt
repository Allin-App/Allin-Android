package fr.iut.alldev.allin.ui.betcreation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun BetCreationScreenBottomText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = AllInTheme.colors.allIn_Purple,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        style = AllInTheme.typography.h2,
        modifier = modifier.fillMaxWidth().alpha(.5f)
    )
}

@Preview
@Composable
private fun BetCreationScreenBottomTextPreview() {
    AllInTheme {
        BetCreationScreenBottomText("Lorem Ipsum...")
    }
}