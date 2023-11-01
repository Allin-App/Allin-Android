package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun AllInAlertDialog(
    enabled: Boolean = true,
    title: String,
    text: String,
    confirmText: String = stringResource(id = R.string.Ok),
    onDismiss: ()->Unit,
    onConfirm: ()->Unit = onDismiss
) {
    if(enabled) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    style = AllInTheme.typography.h1
                )
            },
            text = {
                Text(
                    text = text,
                    style = AllInTheme.typography.r
                )
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirm
                ) {
                    Text(
                        text = confirmText,
                        fontSize = 15.sp,
                        style = AllInTheme.typography.h1.copy(
                            brush = AllInTheme.colors.allIn_MainGradient
                        )
                    )
                }

            },
            onDismissRequest = onDismiss,
            containerColor = AllInTheme.themeColors.main_surface,
            titleContentColor = AllInTheme.themeColors.on_main_surface,
            textContentColor = AllInTheme.themeColors.on_background_2,
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInAlertDialogPreview() {
    AllInTheme {
        AllInAlertDialog(
            title = "Titre",
            text = "Lorem Ipsum",
            onDismiss = {}
        )
    }
}