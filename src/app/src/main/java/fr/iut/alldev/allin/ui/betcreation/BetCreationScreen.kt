package fr.iut.alldev.allin.ui.betcreation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.betcreation.tabs.BetCreationScreenAnswerTab
import fr.iut.alldev.allin.ui.betcreation.tabs.BetCreationScreenQuestionTab
import fr.iut.alldev.allin.ui.core.AllInSections
import fr.iut.alldev.allin.ui.core.RainbowButton
import fr.iut.alldev.allin.ui.core.SectionElement

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
    Box(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        AllInSections(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxSize(),
            sections = listOf(
                SectionElement(stringResource(id = R.string.Question)){
                    BetCreationScreenQuestionTab(
                        isPublic = isPublic,
                        setIsPublic = { isPublic = it },
                        betPhrase = phrase,
                        setBetPhrase = { phrase = it },
                        betTheme = theme,
                        setBetTheme = { theme = it }
                    )
                },
                SectionElement(stringResource(id = R.string.Answer)){
                    BetCreationScreenAnswerTab()
                }
            )
        )
        RainbowButton(
            text = stringResource(id = R.string.Publish),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 14.dp),
            onClick = {
            }
        )
    }
}