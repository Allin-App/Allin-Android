package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@Composable
fun AllInButton(
    color: Color,
    text: String,
    textColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = AllInTheme.typography.h2,
    enabled: Boolean = true,
    isSmall: Boolean = false,
    radius: Dp = 10.dp,
) {
    Button(
        shape = AbsoluteSmoothCornerShape(radius, smoothnessAsPercent = 100),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            disabledContainerColor = AllInTheme.colors.disabled
        ),
        modifier = modifier,
        enabled = enabled,
        onClick = onClick
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = textStyle,
            color = if (enabled) textColor else AllInTheme.colors.disabledBorder,
            fontSize = if (isSmall) 15.sp else 20.sp,
            modifier = Modifier.padding(vertical = if (isSmall) 0.dp else 8.dp)
        )
    }
}

@Preview
@Composable
private fun AllInButtonPreview() {
    AllInTheme {
        AllInButton(
            color = AllInColorToken.allInLoginPurple,
            text = "Connexion",
            textColor = Color.White,
            onClick = { }
        )
    }
}

@Preview
@Composable
private fun AllInButtonDisabledPreview() {
    AllInTheme {
        AllInButton(
            color = AllInColorToken.allInLoginPurple,
            text = "Connexion",
            textColor = Color.White,
            enabled = false,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun AllInButtonSmallPreview() {
    AllInTheme {
        AllInButton(
            color = AllInColorToken.allInLoginPurple,
            text = "Connexion",
            textColor = Color.White,
            isSmall = true,
            onClick = { }
        )
    }
}

@Preview
@Composable
private fun AllInButtonDisabledSmallPreview() {
    AllInTheme {
        AllInButton(
            color = AllInColorToken.allInLoginPurple,
            text = "Connexion",
            textColor = Color.White,
            enabled = false,
            isSmall = true,
            onClick = {}
        )
    }
}