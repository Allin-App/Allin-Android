package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme


enum class IconPosition {
    LEADING,
    TRAILING
}

@Composable
fun AllInTextIcon(
    modifier: Modifier = Modifier,
    text: String,
    icon: Painter,
    color: Color,
    brush: Brush? = null,
    textStyle: TextStyle = AllInTheme.typography.h1,
    position: IconPosition = IconPosition.TRAILING,
    size: Int = 15,
    iconSize: Int = size,
) {
    val direction =
        if (position == IconPosition.LEADING) LayoutDirection.Rtl
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
                    style = brush?.let { textStyle.copy(brush = it) } ?: textStyle,
                    fontSize = size.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(iconSize.dp),
                    tint = color,
                    brush = brush
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
            icon = rememberVectorPainter(image = Icons.Default.Fireplace),
            color = AllInColorToken.allInBlue
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
            color = AllInColorToken.allInBlue,
            brush = AllInColorToken.allInMainGradient,
            position = IconPosition.LEADING
        )
    }
}