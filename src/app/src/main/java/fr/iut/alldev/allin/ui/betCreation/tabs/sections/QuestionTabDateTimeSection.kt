package fr.iut.alldev.allin.ui.betCreation.tabs.sections

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.betCreation.components.BetCreationScreenDateTimeRow
import fr.iut.alldev.allin.ui.core.AllInErrorLine
import fr.iut.alldev.allin.ui.core.AllInTitleInfo

@Composable
internal fun QuestionTabDateTimeSection(
    setRegisterDateDialog: (Boolean) -> Unit,
    setEndDateDialog: (Boolean) -> Unit,
    setRegisterTimeDialog: (Boolean) -> Unit,
    setEndTimeDialog: (Boolean) -> Unit,
    registerDateError: String?,
    betDateError: String?,
    registerDate: String,
    registerTime: String,
    endDate: String,
    endTime: String,
    interactionSource: MutableInteractionSource
) {
    AllInTitleInfo(
        text = stringResource(id = R.string.bet_creation_end_registration_date),
        icon = Icons.AutoMirrored.Outlined.HelpOutline,
        modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
        tooltipText = stringResource(id = R.string.bet_creation_register_end_date_tooltip),
        interactionSource = interactionSource
    )
    BetCreationScreenDateTimeRow(
        date = registerDate,
        time = registerTime,
        onClickDate = { setRegisterDateDialog(true) },
        onClickTime = { setRegisterTimeDialog(true) },
    )
    registerDateError?.let {
        AllInErrorLine(text = it)
    }
    Spacer(modifier = Modifier.height(12.dp))
    AllInTitleInfo(
        text = stringResource(id = R.string.bet_creation_end_bet_date),
        icon = Icons.AutoMirrored.Outlined.HelpOutline,
        modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
        tooltipText = stringResource(id = R.string.bet_creation_bet_end_date_tooltip),
        interactionSource = interactionSource
    )
    BetCreationScreenDateTimeRow(
        date = endDate,
        time = endTime,
        onClickDate = { setEndDateDialog(true) },
        onClickTime = { setEndTimeDialog(true) },
    )
    betDateError?.let {
        AllInErrorLine(text = it)
    }
}