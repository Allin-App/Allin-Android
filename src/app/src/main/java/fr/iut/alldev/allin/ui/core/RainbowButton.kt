package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun RainbowButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    rippleColor: Color = AllInColorToken.allInBlue,
) {
    AllInRipple(rippleColor) {
        AllInCard(
            borderWidth = 1.dp,
            onClick = onClick,
            modifier = modifier.fillMaxWidth(),
            enabled = enabled
        ) {
            val textStyle =
                with(AllInTheme.typography.h2) {
                    if (enabled) {
                        copy(
                            brush = AllInColorToken.allInTextGradient
                        )
                    } else {
                        copy(
                            color = AllInTheme.colors.disabledBorder
                        )
                    }
                }
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = textStyle,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxWidth(),
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RainbowButtonPreview() {
    AllInTheme {
        RainbowButton(text = "Participer", onClick = { })
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RainbowButtonDisabledPreview() {
    AllInTheme {
        RainbowButton(text = "Participer", onClick = { }, enabled = false)
    }
}