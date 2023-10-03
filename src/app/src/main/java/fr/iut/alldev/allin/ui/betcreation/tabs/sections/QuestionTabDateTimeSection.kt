package fr.iut.alldev.allin.ui.betcreation.tabs.sections

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
import fr.iut.alldev.allin.ui.betcreation.components.BetCreationScreenDateTimeRow
import fr.iut.alldev.allin.ui.core.AllInTitleInfo

@Composable
internal fun QuestionTabDateTimeSection(
    setRegisterDateDialog: (Boolean)->Unit,
    setEndDateDialog: (Boolean)->Unit,
    setRegisterTimeDialog: (Boolean)->Unit,
    setEndTimeDialog: (Boolean)->Unit,
    registerDate: String,
    registerTime: String,
    endDate: String,
    endTime: String,
    interactionSource: MutableInteractionSource
) {
    AllInTitleInfo(
        text = stringResource(id = R.string.End_registration_date),
        icon = Icons.AutoMirrored.Outlined.HelpOutline,
        modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
        tooltipText = stringResource(id = R.string.Register_tooltip),
        interactionSource = interactionSource
    )
    BetCreationScreenDateTimeRow(
        date = registerDate,
        time = registerTime,
        onClickDate = { setRegisterDateDialog(true) },
        onClickTime = { setRegisterTimeDialog(true) },
    )
    Spacer(modifier = Modifier.height(12.dp))
    AllInTitleInfo(
        text = stringResource(id = R.string.End_bet_date),
        icon = Icons.AutoMirrored.Outlined.HelpOutline,
        modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
        tooltipText = stringResource(id = R.string.BetEnd_tooltip),
        interactionSource = interactionSource
    )
    BetCreationScreenDateTimeRow(
        date = endDate,
        time = endTime,
        onClickDate = { setEndDateDialog(true) },
        onClickTime = { setEndTimeDialog(true) },
    )
}