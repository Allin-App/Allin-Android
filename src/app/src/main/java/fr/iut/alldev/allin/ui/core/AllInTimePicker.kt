package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.theme.AllInTheme
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllInTimePicker(
    hour: Int?,
    minutes: Int?,
    onSelectHour: (Int, Int) -> Unit,
    onDismiss: () -> Unit
) {
    val calendar = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        is24Hour = true,
        initialHour = hour ?: calendar.get(Calendar.HOUR_OF_DAY),
        initialMinute = minutes ?: calendar.get(Calendar.MINUTE)
    )


    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onSelectHour(timePickerState.hour, timePickerState.minute)
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
        text = {
            TimePicker(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    selectorColor = AllInTheme.colors.allIn_Purple,
                    containerColor = AllInTheme.themeColors.background,
                    clockDialColor = AllInTheme.themeColors.background_2,
                    clockDialUnselectedContentColor = AllInTheme.themeColors.on_main_surface,
                    clockDialSelectedContentColor = AllInTheme.themeColors.background_2,

                )
            )
        },
        containerColor = AllInTheme.themeColors.main_surface
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInTimePickerPreview() {
    AllInTheme {
        AllInTimePicker(
            hour = 13,
            minutes = 13,
            onSelectHour = { h, m -> },
            onDismiss = {}
        )
    }
}