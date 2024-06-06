package fr.iut.alldev.allin.ui.betCreation.tabs

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.data.ext.formatToMediumDate
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.betCreation.tabs.sections.QuestionTabDateTimeSection
import fr.iut.alldev.allin.ui.betCreation.tabs.sections.QuestionTabPrivacySection
import fr.iut.alldev.allin.ui.betCreation.tabs.sections.QuestionTabThemePhraseSection
import java.time.ZonedDateTime
import fr.iut.alldev.allin.data.ext.formatToTime as formatToTime1

@Composable
fun BetCreationScreenQuestionTab(
    modifier: Modifier = Modifier,
    friends: List<User>,
    betTheme: String,
    betThemeError: String?,
    setBetTheme: (String) -> Unit,
    betPhrase: String,
    betPhraseError: String?,
    setBetPhrase: (String) -> Unit,
    isPrivate: Boolean,
    setIsPrivate: (Boolean) -> Unit,
    registerDate: ZonedDateTime,
    registerDateError: String?,
    betDate: ZonedDateTime,
    betDateError: String?,
    selectedFriends: List<String>,
    setRegisterDateDialog: (Boolean) -> Unit,
    setEndDateDialog: (Boolean) -> Unit,
    setRegisterTimeDialog: (Boolean) -> Unit,
    setEndTimeDialog: (Boolean) -> Unit,
    toggleFriend: (String) -> Unit,
    interactionSource: MutableInteractionSource
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 120.dp),
        verticalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        item {
            QuestionTabThemePhraseSection(
                betTheme = betTheme,
                betThemeError = betThemeError,
                setBetTheme = setBetTheme,
                betPhrase = betPhrase,
                betPhraseError = betPhraseError,
                setBetPhrase = setBetPhrase,
                interactionSource = interactionSource
            )
        }
        item {
            QuestionTabDateTimeSection(
                registerDate = registerDate.formatToMediumDate(),
                registerTime = registerDate.formatToTime1(),
                registerDateError = registerDateError,
                betDateError = betDateError,
                endDate = betDate.formatToMediumDate(),
                endTime = betDate.formatToTime1(),
                setEndDateDialog = setEndDateDialog,
                setRegisterDateDialog = setRegisterDateDialog,
                setRegisterTimeDialog = setRegisterTimeDialog,
                setEndTimeDialog = setEndTimeDialog,
                interactionSource = interactionSource,
            )
        }
        item {
            QuestionTabPrivacySection(
                isPrivate = isPrivate,
                setIsPrivate = setIsPrivate,
                friends = friends,
                selectedFriends = selectedFriends,
                toggleFriend = toggleFriend,
                interactionSource = interactionSource

            )
        }
    }
}

@Preview
@Composable
private fun BetCreationScreenQuestionTabPreview() {
    AllInTheme {
        BetCreationScreenQuestionTab(
            friends = emptyList(),
            betTheme = "Elly",
            betThemeError = null,
            setBetTheme = { },
            betPhrase = "Trinh",
            betPhraseError = null,
            setBetPhrase = { },
            isPrivate = true,
            setIsPrivate = { },
            registerDate = ZonedDateTime.now(),
            registerDateError = null,
            betDate = ZonedDateTime.now(),
            betDateError = null,
            selectedFriends = mutableListOf(),
            setRegisterDateDialog = { },
            setEndDateDialog = { },
            setRegisterTimeDialog = { },
            setEndTimeDialog = { },
            toggleFriend = { },
            interactionSource = remember { MutableInteractionSource() }
        )
    }
}