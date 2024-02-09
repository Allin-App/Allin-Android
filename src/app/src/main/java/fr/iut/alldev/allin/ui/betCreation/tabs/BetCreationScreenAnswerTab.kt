package fr.iut.alldev.allin.ui.betCreation.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.bet.BetType
import fr.iut.alldev.allin.ext.getTitleId
import fr.iut.alldev.allin.ui.betCreation.components.BetCreationScreenBottomText
import fr.iut.alldev.allin.ui.core.AllInSelectionBox
import fr.iut.alldev.allin.ui.core.SelectionElement

@Composable
fun BetCreationScreenAnswerTab(
    modifier: Modifier = Modifier,
    selected: SelectionElement?,
    selectedBetType: BetType,
    setSelected: (SelectionElement) -> Unit,
    elements: List<SelectionElement>,
) {
    var isOpen by remember {
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
        when (selectedBetType) {
            BetType.BINARY -> {
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
                    text = stringResource(selectedBetType.getTitleId())
                )
            }

            BetType.CUSTOM -> {
                BetCreationScreenBottomText(
                    text = stringResource(selectedBetType.getTitleId())
                )
            }
        }
    }
}