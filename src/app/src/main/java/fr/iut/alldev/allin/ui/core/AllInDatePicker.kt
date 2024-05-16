package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import java.time.ZonedDateTime
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllInDatePicker(
    currentDate: ZonedDateTime?,
    onSelectDate: (Long) -> Unit,
    onDismiss: () -> Unit,
) {
    val calendar = Calendar.getInstance()
    val realDate = currentDate?.toEpochSecond()?.let {
        val offset = calendar.timeZone.getOffset(it * 1000L)
        (it * 1000L) + offset
    }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = realDate
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let {
                        onSelectDate(it)
                    } ?: run {
                        onDismiss()
                    }
                }
            ) {
                Text(
                    text = stringResource(id = R.string.generic_validate),
                    style = AllInTheme.typography.h1.copy(
                        brush = AllInColorToken.allInMainGradient
                    )
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(id = R.string.generic_cancel),
                    color = AllInTheme.colors.onBackground2,
                    style = AllInTheme.typography.sm1
                )
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = AllInTheme.colors.mainSurface
        )
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                todayDateBorderColor = AllInColorToken.allInBlue,
                selectedDayContainerColor = AllInColorToken.allInBlue,
                todayContentColor = AllInColorToken.allInBlue,
                dayContentColor = AllInColorToken.allInBlue,
                dividerColor = AllInTheme.colors.border,
                containerColor = AllInTheme.colors.background,
                titleContentColor = AllInTheme.colors.onMainSurface,
                headlineContentColor = AllInTheme.colors.onMainSurface,
                subheadContentColor = AllInTheme.colors.onBackground2,
                dateTextFieldColors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = AllInTheme.colors.mainSurface,
                    unfocusedContainerColor = AllInTheme.colors.mainSurface,
                    focusedBorderColor = AllInColorToken.allInPurple,
                    unfocusedBorderColor = AllInTheme.colors.border,
                    focusedTextColor = AllInTheme.colors.onMainSurface,
                    unfocusedTextColor = AllInTheme.colors.onMainSurface,
                    focusedLabelColor = AllInColorToken.allInPurple,
                    unfocusedLabelColor = AllInTheme.colors.onMainSurface,
                )
            )
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInDatePickerPreview() {
    AllInTheme {
        AllInDatePicker(
            currentDate = ZonedDateTime.now(),
            onSelectDate = {},
            onDismiss = {}
        )
    }
}