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
import fr.iut.alldev.allin.ext.shadow
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun AllInGradientButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    AllInCard(
        onClick = onClick,
        modifier = modifier
            .shadow(
                colors = listOf(
                    AllInTheme.colors.allInPink,
                    AllInTheme.colors.allInBlue
                ),
                blurRadius = 20.dp,
                alpha = .5f,
                cornerRadius = 15.dp
            )
            .fillMaxWidth(),
        radius = 10.dp,
        backgroundBrush = AllInTheme.colors.allInMainGradient
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = AllInTheme.typography.h2,
            color = AllInTheme.colors.white,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(vertical = 15.dp)
                .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun AllInGradientButtonPreview() {
    AllInTheme {
        AllInGradientButton(
            text = "Connexion"
        ) {

        }
    }
}