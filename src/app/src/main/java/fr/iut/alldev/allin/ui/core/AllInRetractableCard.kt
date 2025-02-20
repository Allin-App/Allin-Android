package fr.iut.alldev.allin.ui.core

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun AllInRetractableCard(
    modifier: Modifier = Modifier,
    text: String,
    boldText: String = "",
    isOpen: Boolean,
    setIsOpen: (Boolean) -> Unit,
    borderWidth: Dp? = null,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    content: @Composable () -> Unit,
) {
    AllInCard(
        modifier = modifier.fillMaxWidth(),
        borderWidth = borderWidth,
        borderColor = AllInColorToken.allInPurple.copy(.5f)
    ) {
        Column(
            Modifier.animateContentSize()
        ) {
            Row(
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { setIsOpen(!isOpen) }
                    )
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                HighlightedText(
                    text = text,
                    query = boldText,
                    highlightStyle = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = AllInTheme.colors.onMainSurface,
                        fontStyle = AllInTheme.typography.h2.fontStyle
                    ),
                    color = AllInTheme.colors.onBackground2,
                    style = AllInTheme.typography.p1,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = with(Icons.Default) {
                        if (isOpen) ExpandLess else ExpandMore
                    },
                    contentDescription = null,
                    tint = AllInColorToken.allInPurple,
                    modifier = Modifier.size(30.dp)
                )
            }
            AnimatedVisibility(isOpen) {
                Column {
                    HorizontalDivider(color = AllInTheme.colors.border)
                    content()
                }
            }
        }
    }
}