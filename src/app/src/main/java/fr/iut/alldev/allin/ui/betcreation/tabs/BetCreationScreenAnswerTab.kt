package fr.iut.alldev.allin.ui.betcreation.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.bet.BetType
import fr.iut.alldev.allin.ext.getTitle
import fr.iut.alldev.allin.ui.betcreation.components.BetCreationScreenBottomText
import fr.iut.alldev.allin.ui.core.AllInSelectionBox
import fr.iut.alldev.allin.ui.core.SelectionElement

@Composable
fun BetCreationScreenAnswerTab(
    modifier: Modifier = Modifier,
    selected: SelectionElement?,
    selectedBetType: BetType,
    setSelected: (SelectionElement)->Unit,
    elements: List<SelectionElement>
) {
    var isOpen by remember{
        mutableStateOf(false)
    }


    Column(modifier) {
        AllInSelectionBox(
            isOpen = isOpen,
            setIsOpen = { isOpen = it },
            selected = selected,
            setSelected = setSelected,
            elements = elements
        )
        Spacer(modifier = Modifier.height(26.dp))
        when(selectedBetType){
            BetType.YES_NO -> {
                Column(
                    modifier = Modifier.padding(vertical = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(17.dp)
                ) {
                    BetCreationScreenBottomText(text = stringResource(id = R.string.yes_no_bottom_text_1))
                    BetCreationScreenBottomText(text = stringResource(id = R.string.yes_no_bottom_text_2))
                }
            }
            BetType.MATCH -> {
                BetCreationScreenBottomText(
                    text = stringResource(selectedBetType.getTitle())
                )
            }
            BetType.CUSTOM -> {
                BetCreationScreenBottomText(
                    text = stringResource(selectedBetType.getTitle())
                )
            }
        }
    }
}