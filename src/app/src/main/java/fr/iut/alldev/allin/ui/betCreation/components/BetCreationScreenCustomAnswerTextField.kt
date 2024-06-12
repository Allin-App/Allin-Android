package fr.iut.alldev.allin.ui.betCreation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInButton
import fr.iut.alldev.allin.ui.core.AllInTextField
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@Composable
fun BetCreationScreenCustomAnswerTextField(
    value: String,
    setValue: (String) -> Unit,
    enabled: Boolean,
    buttonEnabled: Boolean,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    AllInTextField(
        value = value,
        enabled = enabled,
        modifier = modifier,
        onValueChange = setValue,
        maxChar = 15,
        trailingContent = {
            AllInButton(
                color = AllInColorToken.allInPurple,
                enabled = enabled && buttonEnabled,
                text = stringResource(id = R.string.generic_add),
                textColor = AllInColorToken.white,
                shape = AbsoluteSmoothCornerShape(
                    cornerRadiusTR = 10.dp,
                    cornerRadiusBR = 10.dp,
                    smoothnessAsPercentTR = 100,
                    smoothnessAsPercentBR = 100
                ),
                onClick = onAdd
            )
        }
    )
}

@Preview
@Composable
private fun BetCreationScreenCustomAnswerTextFieldPreview() {
    AllInTheme {
        BetCreationScreenCustomAnswerTextField(
            onAdd = {},
            enabled = true,
            buttonEnabled = true,
            value = "Test",
            setValue = { }
        )
    }
}

@Preview
@Composable
private fun BetCreationScreenCustomAnswerDisabledTextFieldPreview() {
    AllInTheme {
        BetCreationScreenCustomAnswerTextField(
            onAdd = {},
            enabled = false,
            buttonEnabled = false,
            value = "Test",
            setValue = { }
        )
    }
}