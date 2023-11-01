package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun AllInButton(
    color: Color,
    text: String,
    textColor: Color,
    radius: Dp = 15.dp,
    modifier: Modifier = Modifier,
    onClick: ()->Unit
) {
    AllInCard(
        onClick = onClick,
        modifier = modifier,
        radius = radius,
        backgroundColor = color
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = AllInTheme.typography.h2,
            color = textColor,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(vertical = 15.dp)
                .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun AllInButtonPreview() {
    AllInTheme {
        AllInButton(
            color = AllInTheme.colors.allIn_LoginPurple,
            text = "Connexion",
            textColor = Color.White
        ) {

        }
    }
}