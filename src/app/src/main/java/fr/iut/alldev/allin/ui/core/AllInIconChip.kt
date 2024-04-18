package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.theme.AllInTheme
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@Composable
fun AllInIconChip(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    radius: Dp = 15.dp,
    selectedColor: Color = AllInTheme.colors.allInPurple,
    unselectedColor: Color = AllInTheme.themeColors.background,
    leadingIcon: ImageVector,
) {
    val contentColor = if (isSelected) AllInTheme.colors.white else selectedColor
    Card(
        modifier = modifier,
        shape = AbsoluteSmoothCornerShape(radius, 100),
        onClick = onClick,
        border = if (!isSelected) BorderStroke(1.dp, AllInTheme.themeColors.border) else null,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) selectedColor else unselectedColor
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 18.dp)
        ) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = contentColor
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = AllInTheme.typography.h1,
                color = contentColor,
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInIconChipPreviewUnselected() {
    AllInTheme {
        AllInIconChip(
            text = "Public",
            isSelected = false,
            leadingIcon = Icons.Default.Public
        )
    }
}

@Preview
@Composable
private fun AllInIconChipPreviewSelected() {
    AllInTheme {
        AllInIconChip(
            text = "Public",
            isSelected = true,
            leadingIcon = Icons.Default.Public
        )
    }
}