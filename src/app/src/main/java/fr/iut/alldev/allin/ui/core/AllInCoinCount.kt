package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun AllInCoinCount(
    modifier: Modifier = Modifier,
    amount: Int,
    color: Color? = null,
    brush: Brush? = null,
    size: Int = 15,
    textStyle: TextStyle = AllInTheme.typography.h1,
    position: IconPosition = IconPosition.TRAILING
) {
    AllInTextIcon(
        text = amount.toString(),
        icon = AllInTheme.icons.allCoins(),
        color = color ?: Color.Black,
        brush = brush,
        position = position,
        textStyle = textStyle,
        size = size,
        modifier = modifier
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInCoinCountPreview() {
    AllInTheme {
        AllInCoinCount(amount = 542, color = AllInTheme.colors.allInPurple)
    }
}