package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun RainbowButton(
    text: String,
    onClick: ()->Unit,
    modifier: Modifier = Modifier
) {
    AllInCard(borderWidth = 1.dp, onClick = onClick, modifier = modifier) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = AllInTheme.typography.h2.copy(
                brush = AllInTheme.colors.allIn_TextGradient
            ),
            fontSize = 30.sp,
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
            )
    }
}

@Preview
@Composable
private fun RainbowButtonPreview() {
    AllInTheme {
        RainbowButton(text = "Participer", onClick = { })
    }
}