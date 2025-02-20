package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun AllInDetailsDrawer(
    text: String = stringResource(id = R.string.bet_status_details_drawer),
    textColor: Color = AllInTheme.colors.onBackground2,
    content: @Composable ColumnScope.() -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isOpen by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.End)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        isOpen = !isOpen
                    }
                )
        ) {
            Text(
                text = text,
                style = AllInTheme.typography.p2,
                color = textColor,
                fontSize = 15.sp
            )
            Icon(
                imageVector = with(Icons.Default) {
                    if (isOpen) ExpandLess else ExpandMore
                },
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(15.dp)
            )
        }
        AnimatedVisibility(visible = isOpen) {
            Column {
                content()
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInDetailsDrawerPreview() {
    AllInTheme {
        AllInDetailsDrawer {}
    }
}