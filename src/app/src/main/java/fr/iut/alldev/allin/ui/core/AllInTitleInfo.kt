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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.ui.theme.AllInTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllInTitleInfo(
    text: String,
    tooltipText: String,
    icon: ImageVector,
    tooltipState: TooltipState  = rememberTooltipState(),
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    scope.launch {
                        if(tooltipState.isVisible){
                            tooltipState.dismiss()
                        }else{
                            tooltipState.show()
                        }
                    }
                }
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
        AllInTooltip(text = tooltipText, state = tooltipState) {
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
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInTitleInfoPreview() {
    AllInTheme {
        AllInTitleInfo(
            text = "Texte",
            icon = Icons.AutoMirrored.Outlined.HelpOutline,
            tooltipText = "Bonjour"
        )
    }
}