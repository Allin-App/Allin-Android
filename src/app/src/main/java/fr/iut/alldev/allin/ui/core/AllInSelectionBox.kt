package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.theme.AllInTheme


class SelectionElement(
    val text: String,
    val iconVector: ImageVector
)

@Composable
fun AllInSelectionLine(
    text: String,
    iconVector: ImageVector,
    modifier: Modifier = Modifier,
    onClick: ()->Unit,
    trailingIcon: ImageVector? = null,
    interactionSource: MutableInteractionSource
){
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
        Icon(
            imageVector = iconVector,
            contentDescription = null,
            tint = AllInTheme.colors.allIn_Purple,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = text,
            color = AllInTheme.colors.allIn_Purple,
            style = AllInTheme.typography.h2,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        trailingIcon?.let {
            Icon(
                imageVector = trailingIcon,
                contentDescription = null,
                tint = AllInTheme.colors.allIn_Purple,
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
    setIsOpen: (Boolean)->Unit,
    selected: SelectionElement,
    setSelected: (SelectionElement)->Unit,
    elements: List<SelectionElement>
) {
    val interactionSource = remember { MutableInteractionSource() }
    AllInCard(modifier){
        Column(
            Modifier.animateContentSize()
        ) {
            AllInSelectionLine(
                text = selected.text,
                iconVector = selected.iconVector,
                onClick = { setIsOpen(!isOpen) },
                interactionSource = interactionSource,
                trailingIcon = with(Icons.Default){
                    if(isOpen) ExpandLess else ExpandMore
                }
            )
            if(isOpen){
                HorizontalDivider(color = AllInTheme.themeColors.border)
                elements.filter { it != selected }.forEach{
                    element ->
                    AllInSelectionLine(
                        text = element.text,
                        iconVector = element.iconVector,
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

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInSelectionBoxClosedPreview() {
    AllInTheme {
        val elements = listOf(
            SelectionElement("Oui/Non", Icons.AutoMirrored.Default.HelpOutline),
            SelectionElement("Sport", Icons.Default.SportsFootball),
            SelectionElement("Perso", Icons.Default.PinEnd)
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

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInSelectionBoxOpenPreview() {
    AllInTheme {
        val elements = listOf(
            SelectionElement("Oui/Non", Icons.AutoMirrored.Default.HelpOutline),
            SelectionElement("Sport", Icons.Default.SportsFootball),
            SelectionElement("Perso", Icons.Default.Edit)
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
