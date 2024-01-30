package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun AllInMarqueeBox(
    backgroundColor: Color = AllInTheme.themeColors.mainSurface,
    backgroundBrush: Brush? = null,
    content: @Composable BoxScope.() -> Unit

) {
    Box(
        modifier = Modifier
            .fillMaxSize().let { itModifier ->
                backgroundBrush?.let {
                    itModifier.background(it)
                } ?: itModifier.background(backgroundColor)
            }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.allin_marquee),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f, true)
                .scale(1.2f)
                .rotate(11f)
                .basicMarquee(spacing = MarqueeSpacing(0.dp)),
            tint = AllInTheme.colors.white.copy(alpha = .05f)
        )
        content()
    }
}

@Preview
@Preview(widthDp = 800, heightDp = 1280)
@Composable
private fun AllInMarqueeBoxPreview() {
    AllInTheme {
        AllInMarqueeBox(
            backgroundBrush = AllInTheme.colors.allInMainGradient,
        ) {
            Text("CONTENT")
        }
    }
}