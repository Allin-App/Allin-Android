package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@Composable
fun AllInChip(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    radius: Dp = 50.dp,
    selectedColor: Color = AllInColorToken.allInPurple,
    unselectedColor: Color = AllInTheme.colors.background,
) {
    Card(
        modifier = modifier,
        shape = AbsoluteSmoothCornerShape(radius, 100),
        onClick = onClick,
        border = if (!isSelected) BorderStroke(1.dp, AllInTheme.colors.border) else null,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) selectedColor else unselectedColor,
            disabledContainerColor = if (isSelected) selectedColor else unselectedColor
        ),
        enabled = enabled
    ) {
        Box{
            Text(
                text = text,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 8.dp, horizontal = 22.dp)
                    .alpha(if (isSelected) 0f else 1f),
                textAlign = TextAlign.Center,
                style = AllInTheme.typography.p1,
                color = AllInTheme.colors.onBackground2
            )
            if (isSelected) {
                Text(
                    text = text,
                    modifier = modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    style = AllInTheme.typography.h1,
                    color = AllInColorToken.white
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInChipPreviewUnselected() {
    AllInTheme {
        AllInChip("Public", isSelected = false)
    }
}

@Preview
@Composable
private fun AllInChipPreviewSelected() {
    AllInTheme {
        AllInChip("Public", isSelected = true)
    }
}