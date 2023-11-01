package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun AllInCoinCount(
    modifier: Modifier = Modifier,
    amount: Int,
    color: Color,
    position: IconPosition = IconPosition.TRAILING
) {
    AllInTextIcon(
        text = amount.toString(),
        icon = AllInTheme.icons.allCoins(),
        color = color,
        position = position,
        modifier = modifier
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInCoinCountPreview() {
    AllInTheme {
        AllInCoinCount(amount = 542, color = AllInTheme.colors.allIn_Purple)
    }
}