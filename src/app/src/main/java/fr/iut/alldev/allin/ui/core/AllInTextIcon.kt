package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.ui.theme.AllInTheme


enum class IconPosition{
    LEADING,
    TRAILING
}

@Composable
fun AllInTextIcon(
    modifier: Modifier = Modifier,
    text: String,
    icon: Painter,
    color: Color,
    position: IconPosition = IconPosition.TRAILING,
    size: Int = 15,
    iconSize: Int = size
) {
    val direction =
        if(position==IconPosition.LEADING) LayoutDirection.Rtl
        else LayoutDirection.Ltr
    Box(modifier) {
        CompositionLocalProvider(
            LocalLayoutDirection provides direction
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Text(
                    text = text,
                    color = color,
                    style = AllInTheme.typography.h1,
                    fontSize = size.sp
                )
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(iconSize.dp),
                    tint = color
                )
            }
        }
    }
}


@Composable
fun AllInTextIcon(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    color: Color,
    position: IconPosition = IconPosition.TRAILING,
    size: Int = 15,
    iconSize: Int = size
) {
    val direction =
        if(position==IconPosition.LEADING) LayoutDirection.Rtl
        else LayoutDirection.Ltr
    Box(modifier) {
        CompositionLocalProvider(
            LocalLayoutDirection provides direction
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Text(
                    text = text,
                    color = color,
                    style = AllInTheme.typography.h1,
                    fontSize = size.sp
                )
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(iconSize.dp),
                    tint = color
                )
            }
        }
    }
}

@Preview
@Composable
private fun AllInTextIconPreview() {
    AllInTheme {
        AllInTextIcon(
            text = "value",
            icon = Icons.Default.Fireplace,
            color = AllInTheme.colors.allIn_Blue
        )
    }
}

@Preview
@Composable
private fun AllInTextIconReversePreview() {
    AllInTheme {
        AllInTextIcon(
            text = "value",
            icon = AllInTheme.icons.allCoins(),
            color = AllInTheme.colors.allIn_Blue,
            position = IconPosition.LEADING
        )
    }
}