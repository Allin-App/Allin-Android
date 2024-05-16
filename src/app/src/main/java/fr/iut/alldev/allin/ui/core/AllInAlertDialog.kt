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
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun AllInAlertDialog(
    enabled: Boolean = true,
    title: String,
    text: String,
    confirmText: String = stringResource(id = R.string.generic_ok),
    onDismiss: () -> Unit,
    onConfirm: () -> Unit = onDismiss,
) {
    if (enabled) {
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
                    style = AllInTheme.typography.p1
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
                            brush = AllInColorToken.allInMainGradient
                        )
                    )
                }

            },
            onDismissRequest = onDismiss,
            containerColor = AllInTheme.colors.mainSurface,
            titleContentColor = AllInTheme.colors.onMainSurface,
            textContentColor = AllInTheme.colors.onBackground2,
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