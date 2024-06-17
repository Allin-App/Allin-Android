package fr.iut.alldev.allin.ui.betCreation.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ext.getTitleId
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.betCreation.BetCreationViewModel
import fr.iut.alldev.allin.ui.betCreation.components.BetCreationScreenBottomText
import fr.iut.alldev.allin.ui.betCreation.components.BetCreationScreenCustomAnswer
import fr.iut.alldev.allin.ui.betCreation.components.BetCreationScreenCustomAnswerTextField
import fr.iut.alldev.allin.ui.core.AllInErrorLine
import fr.iut.alldev.allin.ui.core.AllInSelectionBox
import fr.iut.alldev.allin.ui.core.SelectionElement

private const val BET_MAX_ANSWERS = 5

@Composable
fun BetCreationScreenAnswerTab(
    typeError: String?,
    modifier: Modifier = Modifier,
    selected: SelectionElement?,
    selectedBetType: BetCreationViewModel.BetTypeState,
    setSelected: (SelectionElement) -> Unit,
    elements: List<SelectionElement>,
    addAnswer: (String) -> Unit,
    deleteAnswer: (String) -> Unit
) {
    var isOpen by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 120.dp),
        verticalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        item {
            AllInSelectionBox(
                isOpen = isOpen,
                setIsOpen = { isOpen = it },
                selected = selected,
                setSelected = setSelected,
                elements = elements
            )
            Spacer(modifier = Modifier.height(26.dp))

            Column(
                modifier = Modifier.padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                when (selectedBetType) {
                    BetCreationViewModel.BetTypeState.Binary -> {
                        BetCreationScreenBottomText(text = stringResource(id = R.string.bet_creation_yes_no_bottom_text_1))
                        BetCreationScreenBottomText(text = stringResource(id = R.string.bet_creation_yes_no_bottom_text_2))
                    }

                    is BetCreationViewModel.BetTypeState.Match -> {
                        BetCreationScreenBottomText(text = stringResource(selectedBetType.type.getTitleId()))
                    }

                    is BetCreationViewModel.BetTypeState.Custom -> {
                        val (currentAnswer, setCurrentAnswer) = remember { mutableStateOf("") }


                        BetCreationScreenBottomText(text = stringResource(id = R.string.bet_creation_custom_bottom_text_1))
                        BetCreationScreenBottomText(text = stringResource(id = R.string.bet_creation_custom_bottom_text_2))
                        BetCreationScreenCustomAnswerTextField(
                            value = currentAnswer,
                            setValue = setCurrentAnswer,
                            onAdd = { addAnswer(currentAnswer) },
                            enabled = selectedBetType.answers.size < BET_MAX_ANSWERS,
                            buttonEnabled = currentAnswer.isNotBlank() && (currentAnswer !in selectedBetType.answers),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = stringResource(
                                id = R.string.bet_creation_max_answers,
                                BET_MAX_ANSWERS - selectedBetType.answers.size
                            ),
                            color = AllInTheme.colors.onMainSurface,
                            style = AllInTheme.typography.sm1,
                            modifier = Modifier.align(Alignment.End)
                        )


                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            selectedBetType.answers.fastForEach {
                                BetCreationScreenCustomAnswer(
                                    text = it,
                                    onDelete = { deleteAnswer(it) }
                                )
                            }
                        }
                    }
                }
                typeError?.let { AllInErrorLine(text = it) }
            }
        }
    }
}

@Preview
@Composable
private fun BetCreationScreenAnswerTabPreview() {
    AllInTheme {
        BetCreationScreenAnswerTab(
            selected = null,
            selectedBetType = BetCreationViewModel.BetTypeState.Binary,
            setSelected = { },
            elements = listOf(),
            addAnswer = { },
            deleteAnswer = { },
            typeError = "Error"
        )
    }
}

@Preview
@Composable
private fun BetCreationScreenAnswerTabCustomPreview() {
    AllInTheme {
        BetCreationScreenAnswerTab(
            selected = null,
            selectedBetType = BetCreationViewModel.BetTypeState.Custom(listOf("Lorem ipsum", "Lorem iiiipsum", "Looooorem")),
            setSelected = { },
            elements = listOf(),
            addAnswer = { },
            deleteAnswer = { },
            typeError = null
        )
    }
}