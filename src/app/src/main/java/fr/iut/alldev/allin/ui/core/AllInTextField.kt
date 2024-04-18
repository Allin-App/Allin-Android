package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import androidx.core.text.isDigitsOnly
import fr.iut.alldev.allin.ext.formatToSimple
import fr.iut.alldev.allin.ext.toFloatOrNull
import fr.iut.alldev.allin.ext.verifyIsFloat
import fr.iut.alldev.allin.theme.AllInTheme
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape
import java.util.Locale

@Composable
fun AllInTextField(
    value: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = AllInTheme.typography.p1,
    placeholder: String? = null,
    maxChar: Int? = null,
    enabled: Boolean = true,
    trailingIcon: Painter? = null,
    trailingContent: @Composable() (() -> Unit)? = null,
    placeholderFontSize: TextUnit = 18.sp,
    multiLine: Boolean = false,
    errorText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    borderColor: Color = AllInTheme.themeColors.onBackground2,
    containerColor: Color = AllInTheme.themeColors.background,
    textColor: Color = AllInTheme.themeColors.onMainSurface,
    placeholderColor: Color = AllInTheme.themeColors.onBackground2,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        isError = errorText != null,
        modifier = modifier,
        supportingText = errorText?.let {
            { AllInErrorLine(text = it) }
        },
        visualTransformation = visualTransformation,
        singleLine = !multiLine,
        onValueChange = {
            if (maxChar == null || it.length <= maxChar) {
                onValueChange(it)
            }
        },
        placeholder = placeholder?.let {
            {
                Text(
                    text = placeholder,
                    fontSize = placeholderFontSize,
                    style = AllInTheme.typography.p1,
                    color = placeholderColor,
                    maxLines = if (multiLine) 3 else 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        trailingIcon = trailingContent ?: trailingIcon?.let {
            @Composable {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = AllInTheme.colors.allInLightGrey300
                )
            }
        },
        textStyle = textStyle,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = keyboardActions,
        shape = AbsoluteSmoothCornerShape(10.dp, 100),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = textColor,
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            errorTextColor = textColor,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            errorContainerColor = containerColor,
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllInPasswordField(
    placeholder: String,
    value: String,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Default,
    keyboardType: KeyboardType = KeyboardType.Password,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    errorText: String? = null,
    onValueChange: (String) -> Unit,
    isHiddenByDefault: Boolean = true,
) {
    var hidden by remember { mutableStateOf(isHiddenByDefault) }
    AllInTextField(
        placeholder = placeholder,
        value = value,
        modifier = modifier,
        trailingContent = {
            IconButton(
                onClick = { hidden = !hidden }
            ) {
                Icon(
                    imageVector = if (hidden) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = null,
                    tint = AllInTheme.colors.allInLightGrey300
                )
            }
        },
        onValueChange = onValueChange,
        errorText = errorText,
        visualTransformation = if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardType = keyboardType,
        imeAction = imeAction,
        keyboardActions = keyboardActions
    )
}

@Composable
fun AllInFloatTextfield(
    value: Float?,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = AllInTheme.typography.p1,
    placeholder: String? = null,
    trailingIcon: Painter? = null,
    maxChar: Int? = 5,
    setValue: (Float?) -> Unit
) {
    val configuration = LocalConfiguration.current
    val locale = remember {
        ConfigurationCompat.getLocales(configuration).get(0) ?: Locale.getDefault()
    }
    var stringValue by remember(value) { mutableStateOf(value?.formatToSimple(locale) ?: "") }

    AllInTextField(
        value = stringValue,
        modifier = modifier,
        placeholder = placeholder,
        maxChar = maxChar,
        textStyle = textStyle,
        trailingIcon = trailingIcon,
        keyboardType = KeyboardType.Number
    ) {
        it.verifyIsFloat(locale)?.let {
            stringValue = it
            if (it.isNotEmpty()) {
                if (it.lastOrNull() !in setOf(',', '.')) {
                    it.toFloatOrNull(locale)?.let {
                        setValue(it)
                    }
                }
            } else setValue(null)
        }
    }
}

@Composable
fun AllInIntTextField(
    value: Int?,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = AllInTheme.typography.p1,
    placeholder: String? = null,
    trailingIcon: Painter? = null,
    maxChar: Int? = 3,
    setValue: (Int?) -> Unit
) {
    AllInTextField(
        value = value?.toString() ?: "",
        placeholder = placeholder,
        modifier = modifier,
        maxChar = maxChar,
        trailingIcon = trailingIcon,
        textStyle = textStyle,
        keyboardType = KeyboardType.NumberPassword
    ) {
        if (it.isEmpty()) setValue(null)
        else if (it.isDigitsOnly()) {
            it.toIntOrNull()?.let {
                setValue(it)
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun AllInTextFieldPlaceholderPreview() {
    AllInTheme {
        AllInTextField(
            placeholder = "Email",
            value = "",
            onValueChange = { }
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInTextFieldValuePreview() {
    AllInTheme {
        AllInTextField(
            placeholder = "Email",
            value = "JohnDoe@mail.com",
            onValueChange = { }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun AllInTextFieldErrorPreview() {
    AllInTheme {
        AllInTextField(
            placeholder = "Email",
            value = "JohnDoe@mail.com",
            onValueChange = { },
            errorText = "This is an error."
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun AllInTextFieldPasswordPreview() {
    AllInTheme {
        val (value, setValue) = mutableStateOf("")
        AllInPasswordField(
            placeholder = "Password",
            value = value,
            onValueChange = setValue
        )
    }
}

@Preview
@Composable
private fun AllInTextFieldMultilinePreview() {
    AllInTheme {
        AllInTextField(
            placeholder = "David sera il absent le Lundi matin en cours ?",
            value = "",
            multiLine = true,
            onValueChange = { }
        )
    }
}