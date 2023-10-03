package fr.iut.alldev.allin.ui.betcreation.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.data.ext.formatToMediumDate
import fr.iut.alldev.allin.data.ext.formatToTime
import fr.iut.alldev.allin.ui.betcreation.tabs.sections.QuestionTabDateTimeSection
import fr.iut.alldev.allin.ui.betcreation.tabs.sections.QuestionTabPrivacySection
import fr.iut.alldev.allin.ui.betcreation.tabs.sections.QuestionTabThemePhraseSection
import java.time.ZonedDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BetCreationScreenQuestionTab(
    modifier: Modifier = Modifier,
    nbFriends: Int,
    betTheme: String,
    setBetTheme: (String)->Unit,
    betPhrase: String,
    setBetPhrase: (String)->Unit,
    isPublic: Boolean,
    setIsPublic: (Boolean)->Unit,
    registerDate: ZonedDateTime,
    betDate: ZonedDateTime,
    selectedFriends: MutableList<Int>,
    setRegisterDateDialog: (Boolean)->Unit,
    setEndDateDialog: (Boolean)->Unit,
    setRegisterTimeDialog: (Boolean)->Unit,
    setEndTimeDialog: (Boolean)->Unit,
    interactionSource: MutableInteractionSource
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    Column(modifier){
        QuestionTabThemePhraseSection(
            betTheme = betTheme,
            setBetTheme = setBetTheme,
            betPhrase = betPhrase,
            setBetPhrase = setBetPhrase,
            bringIntoViewRequester = bringIntoViewRequester,
            interactionSource = interactionSource
        )
        Spacer(modifier = Modifier.height(35.dp))
        QuestionTabDateTimeSection(
            registerDate = registerDate.formatToMediumDate(),
            registerTime = registerDate.formatToTime(),
            endDate = betDate.formatToMediumDate(),
            endTime = betDate.formatToTime(),
            setEndDateDialog = setEndDateDialog,
            setRegisterDateDialog = setRegisterDateDialog,
            setRegisterTimeDialog = setRegisterTimeDialog,
            setEndTimeDialog = setEndTimeDialog,
            interactionSource = interactionSource,
        )
        Spacer(modifier = Modifier.height(35.dp))
        QuestionTabPrivacySection(
            isPublic = isPublic,
            setIsPublic = setIsPublic,
            nbFriends = nbFriends,
            selectedFriends = selectedFriends,
            interactionSource = interactionSource
        )
    }
}