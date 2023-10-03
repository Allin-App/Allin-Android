package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private fun launchTooltip(
    scope: CoroutineScope,
    tooltipState: TooltipState
){
    scope.launch {
        tooltipState.show()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun AllInTitleInfo(
    text: String,
    tooltipText: String,
    icon: ImageVector,
    tooltipState: TooltipState  = rememberTooltipState(isPersistent = true),
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val scope = rememberCoroutineScope()
    Row(
        modifier = modifier
            .combinedClickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { launchTooltip(scope, tooltipState) },
                onDoubleClick = { launchTooltip(scope, tooltipState) },
                onLongClick = { launchTooltip(scope, tooltipState) },
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