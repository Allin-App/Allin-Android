package fr.iut.alldev.allin.ui.betstatus.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.alldev.allin.ui.core.AllInTextIcon
import fr.iut.alldev.allin.ui.core.IconPosition
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun YesNoDetailsLine(
    icon: ImageVector,
    yesText: String,
    noText: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AllInTextIcon(
            text = yesText,
            color = AllInTheme.colors.allIn_Blue,
            icon = icon,
            position = IconPosition.LEADING,
            size = 10,
            iconSize = 15
        )
        AllInTextIcon(
            text = noText,
            color = AllInTheme.colors.allIn_BarPink,
            icon = icon,
            position = IconPosition.TRAILING,
            size = 10,
            iconSize = 15
        )
    }
}

@Composable
fun YesNoDetailsLine(
    icon: Painter,
    yesText: String,
    noText: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AllInTextIcon(
            text = yesText,
            color = AllInTheme.colors.allIn_Blue,
            icon = icon,
            position = IconPosition.LEADING,
            size = 10,
            iconSize = 15
        )
        AllInTextIcon(
            text = noText,
            color = AllInTheme.colors.allIn_BarPink,
            icon = icon,
            position = IconPosition.TRAILING,
            size = 10,
            iconSize = 15
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun YesNoDetailsLinePreview() {
    AllInTheme {
        YesNoDetailsLine(
            icon = AllInTheme.icons.allCoins(),
            yesText = "550",
            noText = "330"
        )
    }
}