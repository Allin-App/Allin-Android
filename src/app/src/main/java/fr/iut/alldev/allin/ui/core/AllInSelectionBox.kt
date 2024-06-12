package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.PinEnd
import androidx.compose.material.icons.filled.SportsFootball
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme


class SelectionElement(
    val textId: Int,
    val imageVector: ImageVector
)

@Composable
private fun AllInSelectionLine(
    text: String,
    iconVector: ImageVector?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    trailingIcon: ImageVector? = null,
    interactionSource: MutableInteractionSource,
) {
    Row(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        iconVector?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = AllInColorToken.allInPurple,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = text,
            color = AllInColorToken.allInPurple,
            style = AllInTheme.typography.h2,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        trailingIcon?.let {
            Icon(
                imageVector = trailingIcon,
                contentDescription = null,
                tint = AllInColorToken.allInPurple,
                modifier = Modifier
                    .size(30.dp)
            )
        }
    }
}


@Composable
fun AllInSelectionBox(
    modifier: Modifier = Modifier,
    isOpen: Boolean,
    borderWidth: Dp? = null,
    setIsOpen: (Boolean) -> Unit,
    selected: SelectionElement?,
    setSelected: (SelectionElement) -> Unit,
    elements: List<SelectionElement>,
) {
    val interactionSource = remember { MutableInteractionSource() }
    AllInCard(
        modifier = modifier.fillMaxWidth(),
        radius = 10.dp,
        borderWidth = borderWidth,
        borderColor = AllInColorToken.allInPurple.copy(alpha = .42f)
    ) {
        Column(
            Modifier.animateContentSize()
        ) {
            AllInSelectionLine(
                text = selected?.let {
                    stringResource(id = it.textId)
                } ?: "",
                iconVector = selected?.let {
                    selected.imageVector
                },
                onClick = { setIsOpen(!isOpen) },
                interactionSource = interactionSource,
                trailingIcon = with(Icons.Default) {
                    if (isOpen) ExpandLess else ExpandMore
                }
            )
            AnimatedVisibility(isOpen) {
                Column {
                    HorizontalDivider(color = AllInTheme.colors.border)
                    elements.filter { it != selected }.forEach { element ->
                        AllInSelectionLine(
                            text = stringResource(id = element.textId),
                            iconVector = element.imageVector,
                            interactionSource = interactionSource,
                            onClick = {
                                setSelected(element)
                                setIsOpen(false)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInSelectionBoxClosedPreview() {
    AllInTheme {
        val elements = listOf(
            SelectionElement(R.string.bet_type_binary, Icons.AutoMirrored.Default.HelpOutline),
            SelectionElement(R.string.bet_type_match, Icons.Default.SportsFootball),
            SelectionElement(R.string.bet_type_custom, Icons.Default.PinEnd)
        )
        AllInSelectionBox(
            isOpen = false,
            selected = elements[0],
            elements = elements,
            setSelected = {},
            setIsOpen = {},
        )
    }
}

@Composable
private fun AllInSelectionLine(
    element: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    trailingIcon: ImageVector? = null,
    interactionSource: MutableInteractionSource
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        element()
        trailingIcon?.let {
            Icon(
                imageVector = trailingIcon,
                contentDescription = null,
                tint = AllInColorToken.allInPurple,
                modifier = Modifier
                    .size(30.dp)
            )
        }
    }
}

@Composable
fun AllInSelectionBox(
    modifier: Modifier = Modifier,
    isOpen: Boolean,
    setIsOpen: (Boolean) -> Unit,
    borderWidth: Dp? = null,
    selected: (@Composable RowScope.() -> Unit)?,
    setSelected: (@Composable RowScope.() -> Unit) -> Unit,
    elements: List<@Composable RowScope.() -> Unit>,
) {
    val interactionSource = remember { MutableInteractionSource() }
    AllInCard(
        modifier = modifier.fillMaxWidth(),
        radius = 10.dp,
        borderWidth = borderWidth,
        borderColor = AllInColorToken.allInPurple.copy(alpha = .42f)
    ) {
        Column(Modifier.animateContentSize()) {
            AllInSelectionLine(
                element = selected ?: { Box { } },
                onClick = { setIsOpen(!isOpen) },
                interactionSource = interactionSource,
                trailingIcon = with(Icons.Default) {
                    if (isOpen) ExpandLess else ExpandMore
                }
            )
            AnimatedVisibility(isOpen) {
                Column {
                    HorizontalDivider(color = AllInTheme.colors.border)
                    elements.filter { it != selected }.forEach { element ->
                        AllInSelectionLine(
                            element = element,
                            interactionSource = interactionSource,
                            onClick = {
                                setSelected(element)
                                setIsOpen(false)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInSelectionBoxOpenPreview() {
    AllInTheme {
        val elements = listOf(
            SelectionElement(R.string.bet_type_binary, Icons.AutoMirrored.Default.HelpOutline),
            SelectionElement(R.string.bet_type_match, Icons.Default.SportsFootball),
            SelectionElement(R.string.bet_type_custom, Icons.Default.Edit)
        )
        AllInSelectionBox(
            isOpen = true,
            selected = elements[0],
            elements = elements,
            setSelected = {},
            setIsOpen = {},
        )
    }
}
