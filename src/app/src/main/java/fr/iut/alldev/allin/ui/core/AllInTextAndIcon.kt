package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun AllInTextAndIcon(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: ()->Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = AllInTheme.typography.h2,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = AllInTheme.themeColors.on_main_surface
        )
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AllInTheme.themeColors.on_main_surface,
            modifier = Modifier
                .size(15.dp)
                .alpha(.8f)
        )
    }

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInTextAndIconPreview() {
    AllInTheme {
        AllInTextAndIcon(text = "Texte", icon = Icons.AutoMirrored.Outlined.HelpOutline) {
        }
    }
}