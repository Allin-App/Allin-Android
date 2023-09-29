package fr.iut.alldev.allin.ui.betcreation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.BetType
import fr.iut.alldev.allin.ext.getIcon
import fr.iut.alldev.allin.ext.getTitle
import fr.iut.alldev.allin.ui.betcreation.tabs.BetCreationScreenAnswerTab
import fr.iut.alldev.allin.ui.betcreation.tabs.BetCreationScreenQuestionTab
import fr.iut.alldev.allin.ui.core.AllInSections
import fr.iut.alldev.allin.ui.core.RainbowButton
import fr.iut.alldev.allin.ui.core.SectionElement
import fr.iut.alldev.allin.ui.core.SelectionElement

@Composable
fun BetCreationScreen(

) {
    var theme by remember{
        mutableStateOf("")
    }
    var phrase by remember{
        mutableStateOf("")
    }
    var isPublic by remember{
        mutableStateOf(true)
    }

    val betTypes = remember {
        BetType.values().toList()
    }

    val selectedFriends = remember {
        mutableListOf<Int>()
    }

    val elements = betTypes.map {
            SelectionElement(
                it.getTitle(),
                it.getIcon()
            )
        }


    var selected by remember {
        mutableStateOf(elements[0])
    }

    val selectedBetType by remember {
        derivedStateOf {
            betTypes[elements.indexOf(selected)]
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        AllInSections(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxSize()
                .padding(bottom = 90.dp),
            sections = listOf(
                SectionElement(stringResource(id = R.string.Question)){
                    BetCreationScreenQuestionTab(
                        isPublic = isPublic,
                        setIsPublic = { isPublic = it },
                        betPhrase = phrase,
                        setBetPhrase = { phrase = it },
                        betTheme = theme,
                        setBetTheme = { theme = it },
                        nbFriends = 42,
                        selectedFriends = selectedFriends,
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(vertical = 12.dp, horizontal = 20.dp)
                    )
                },
                SectionElement(stringResource(id = R.string.Answer)){
                    BetCreationScreenAnswerTab(
                        selectedBetType = selectedBetType,
                        selected = selected,
                        setSelected = { selected = it },
                        elements = elements,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 12.dp, horizontal = 20.dp)
                    )
                }
            )
        )
        RainbowButton(
            text = stringResource(id = R.string.Publish),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 14.dp)
                .padding(horizontal = 20.dp),
            onClick = {
            }
        )
    }
}