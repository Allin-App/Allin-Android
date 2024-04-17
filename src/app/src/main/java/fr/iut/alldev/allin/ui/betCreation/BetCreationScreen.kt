package fr.iut.alldev.allin.ui.betCreation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.bet.BetType
import fr.iut.alldev.allin.ext.getIcon
import fr.iut.alldev.allin.ext.getTitleId
import fr.iut.alldev.allin.ui.betCreation.components.BetCreationScreenContent
import fr.iut.alldev.allin.ui.core.AllInAlertDialog
import fr.iut.alldev.allin.ui.core.AllInDatePicker
import fr.iut.alldev.allin.ui.core.AllInTimePicker
import fr.iut.alldev.allin.ui.core.SelectionElement
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@Composable
fun BetCreationScreen(
    viewModel: BetCreationViewModel = hiltViewModel(),
    setLoading: (Boolean) -> Unit,
    openDrawer: () -> Unit,
    onCreation: () -> Unit
) {
    val betTypes = remember { BetType.entries }
    var hasError by remember { mutableStateOf(false) }

    var theme by remember { viewModel.theme }
    var phrase by remember { viewModel.phrase }
    val (registerDate, setRegisterDate) = remember { viewModel.registerDate }
    val (betDate, setBetDate) = remember { viewModel.betDate }
    var isPublic by remember { viewModel.isPublic }
    var selectedBetType by remember { viewModel.selectedBetType }

    val themeError by remember { viewModel.themeError }
    val phraseError by remember { viewModel.phraseError }
    val registerDateError by remember { viewModel.registerDateError }
    val betDateError by remember { viewModel.betDateError }

    val selectedFriends = remember { mutableListOf<Int>() }
    var selectionElements by remember { mutableStateOf(listOf<SelectionElement>()) }
    var selectedBetTypeElement by remember { mutableStateOf<SelectionElement?>(null) }

    val themeFieldName = stringResource(id = R.string.Theme)
    val phraseFieldName = stringResource(id = R.string.Bet_Phrase)

    LaunchedEffect(key1 = betTypes) {
        selectionElements = betTypes.map {
            SelectionElement(
                textId = it.getTitleId(),
                imageVector = it.getIcon()
            )
        }
        selectedBetTypeElement = selectionElements.getOrNull(0)
    }

    LaunchedEffect(key1 = selectedBetTypeElement) {
        selectedBetType = betTypes[selectionElements.indexOf(selectedBetTypeElement)]
    }

    val (showRegisterDatePicker, setRegisterDatePicker) = remember { mutableStateOf(false) }
    val (showEndDatePicker, setEndDatePicker) = remember { mutableStateOf(false) }

    val (showRegisterTimePicker, setRegisterTimePicker) = remember { mutableStateOf(false) }
    val (showEndTimePicker, setEndTimePicker) = remember { mutableStateOf(false) }

    BetCreationScreenContent(
        nbFriends = 42,
        betTheme = theme,
        betThemeError = themeError.errorResource(),
        setBetTheme = { theme = it },
        betPhrase = phrase,
        betPhraseError = phraseError.errorResource(),
        setBetPhrase = { phrase = it },
        isPublic = isPublic,
        setIsPublic = { isPublic = it },
        registerDate = registerDate,
        registerDateError = registerDateError.errorResource(),
        betDate = betDate,
        betDateError = betDateError.errorResource(),
        selectedFriends = selectedFriends,
        setRegisterDateDialog = setRegisterDatePicker,
        setEndDateDialog = setEndDatePicker,
        setRegisterTimeDialog = setRegisterTimePicker,
        setEndTimeDialog = setEndTimePicker,
        selectedBetTypeElement = selectedBetTypeElement,
        selectedBetType = selectedBetType,
        setSelectedBetTypeElement = { selectedBetTypeElement = it },
        selectionBetType = selectionElements,
        openDrawer = openDrawer,
        onCreateBet = {
            viewModel.createBet(
                themeFieldName = themeFieldName,
                phraseFieldName = phraseFieldName,
                setLoading = setLoading,
                onError = { hasError = true },
                onSuccess = {
                    onCreation()
                }
            )
        }
    )

    if (showRegisterDatePicker || showEndDatePicker) {
        val dateToEdit = if (showRegisterDatePicker) registerDate else betDate
        AllInDatePicker(
            currentDate = if (showRegisterDatePicker) registerDate else betDate,
            onSelectDate = { date ->
                val selectedDate =
                    ZonedDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault())
                val newDate = dateToEdit
                    .withYear(selectedDate.year)
                    .withMonth(selectedDate.month.value)
                    .withDayOfMonth(selectedDate.dayOfMonth)
                    .withDayOfYear(selectedDate.dayOfYear)
                if (showRegisterDatePicker) {
                    setRegisterDate(newDate)
                } else {
                    setBetDate(newDate)
                }
                setRegisterDatePicker(false)
                setEndDatePicker(false)
            },
            onDismiss = {
                setRegisterDatePicker(false)
                setEndDatePicker(false)
            }
        )
    }

    AllInAlertDialog(
        enabled = hasError,
        title = stringResource(id = R.string.generic_error),
        text = stringResource(id = R.string.bet_creation_error),
        onDismiss = { hasError = false }
    )

    if (showRegisterTimePicker || showEndTimePicker) {
        val timeToEdit = if (showRegisterTimePicker) registerDate else betDate
        AllInTimePicker(
            hour = timeToEdit.hour,
            minutes = timeToEdit.minute,
            onSelectHour = { hour, min ->
                val time = (timeToEdit)
                    .withHour(hour)
                    .withMinute(min)
                if (showRegisterTimePicker) {
                    setRegisterDate(time)
                } else {
                    setBetDate(time)
                }
                setRegisterTimePicker(false)
                setEndTimePicker(false)
            },
            onDismiss = {
                setRegisterTimePicker(false)
                setEndTimePicker(false)
            }
        )
    }

}