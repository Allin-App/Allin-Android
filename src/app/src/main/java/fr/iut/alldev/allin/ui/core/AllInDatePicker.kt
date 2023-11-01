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
    onDismiss: () -> Unit
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
                        brush = AllInTheme.colors.allIn_MainGradient
                    )
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(id = R.string.Cancel),
                    color = AllInTheme.themeColors.on_background_2,
                    style = AllInTheme.typography.h3
                )
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = AllInTheme.themeColors.main_surface
        )
    ){
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                todayDateBorderColor = AllInTheme.colors.allIn_Blue,
                selectedDayContainerColor = AllInTheme.colors.allIn_Blue,
                todayContentColor = AllInTheme.colors.allIn_Blue,
                dayContentColor = AllInTheme.colors.allIn_Blue,
                dividerColor = AllInTheme.themeColors.border,
                containerColor = AllInTheme.themeColors.background,
                titleContentColor = AllInTheme.themeColors.on_main_surface,
                headlineContentColor = AllInTheme.themeColors.on_main_surface,
                subheadContentColor = AllInTheme.themeColors.on_background_2,
                dateTextFieldColors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = AllInTheme.themeColors.main_surface,
                    unfocusedContainerColor = AllInTheme.themeColors.main_surface,
                    focusedBorderColor = AllInTheme.colors.allIn_Purple,
                    unfocusedBorderColor = AllInTheme.themeColors.border,
                    focusedTextColor = AllInTheme.themeColors.on_main_surface,
                    unfocusedTextColor = AllInTheme.themeColors.on_main_surface,
                    focusedLabelColor = AllInTheme.colors.allIn_Purple,
                    unfocusedLabelColor = AllInTheme.themeColors.on_main_surface,
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