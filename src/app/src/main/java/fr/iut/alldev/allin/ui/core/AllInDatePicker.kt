package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.alldev.allin.R
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
                    text = stringResource(id = R.string.Validate),
                    style = AllInTheme.typography.h1.copy(
                        brush = AllInTheme.colors.allInMainGradient
                    )
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(id = R.string.Cancel),
                    color = AllInTheme.themeColors.onBackground2,
                    style = AllInTheme.typography.sm1
                )
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = AllInTheme.themeColors.mainSurface
        )
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                todayDateBorderColor = AllInTheme.colors.allInBlue,
                selectedDayContainerColor = AllInTheme.colors.allInBlue,
                todayContentColor = AllInTheme.colors.allInBlue,
                dayContentColor = AllInTheme.colors.allInBlue,
                dividerColor = AllInTheme.themeColors.border,
                containerColor = AllInTheme.themeColors.background,
                titleContentColor = AllInTheme.themeColors.onMainSurface,
                headlineContentColor = AllInTheme.themeColors.onMainSurface,
                subheadContentColor = AllInTheme.themeColors.onBackground2,
                dateTextFieldColors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = AllInTheme.themeColors.mainSurface,
                    unfocusedContainerColor = AllInTheme.themeColors.mainSurface,
                    focusedBorderColor = AllInTheme.colors.allInPurple,
                    unfocusedBorderColor = AllInTheme.themeColors.border,
                    focusedTextColor = AllInTheme.themeColors.onMainSurface,
                    unfocusedTextColor = AllInTheme.themeColors.onMainSurface,
                    focusedLabelColor = AllInTheme.colors.allInPurple,
                    unfocusedLabelColor = AllInTheme.themeColors.onMainSurface,
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