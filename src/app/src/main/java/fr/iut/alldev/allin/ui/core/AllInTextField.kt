package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.ui.theme.AllInTheme
import kotlinx.coroutines.launch
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllInTextField(
    placeholder: String,
    value: String,
    maxChar: Int? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    trailingIcon: ImageVector? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    placeholderFontSize: TextUnit = 18.sp,
    multiLine: Boolean = false,
    onValueChange: (String)->Unit,
    bringIntoViewRequester: BringIntoViewRequester,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    borderColor: Color = AllInTheme.colors.allIn_LightGrey300
) {
    val scope = rememberCoroutineScope()
    var hasFocus by remember { mutableStateOf(false) }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(text = value, selection = TextRange(value.length)))
    }

    LaunchedEffect(key1 = value, block = {
        textFieldValue = TextFieldValue(text = value, selection = TextRange(value.length))
    })

    OutlinedTextField(
        value = textFieldValue,
        modifier = modifier
            .onFocusChanged {
            hasFocus = it.hasFocus
            if (it.isFocused) {
                scope.launch {
                    bringIntoViewRequester.bringIntoView()
                }
            }
        },
        visualTransformation = visualTransformation,
        singleLine = !multiLine,
        onValueChange = {
            if(maxChar==null || it.text.length<=maxChar) {
                textFieldValue = it
                onValueChange(it.text)
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                fontSize = placeholderFontSize,
                style = AllInTheme.typography.r,
                color = AllInTheme.colors.allIn_LightGrey300,
                maxLines = if(multiLine) 3 else 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        trailingIcon = trailingContent ?: trailingIcon?.let{
            @Composable {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = AllInTheme.colors.allIn_LightGrey300
                )
            }
        },
        textStyle = AllInTheme.typography.r,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        keyboardActions = keyboardActions,
        shape = AbsoluteSmoothCornerShape(10.dp, 100),
        colors =  OutlinedTextFieldDefaults.colors(
            cursorColor = AllInTheme.colors.allIn_Dark,
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            focusedTextColor = AllInTheme.colors.allIn_Dark,
            unfocusedTextColor = AllInTheme.colors.allIn_Dark,
            focusedContainerColor = AllInTheme.colors.white,
            unfocusedContainerColor = AllInTheme.colors.white
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllInPasswordField(
    placeholder: String,
    value: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Password,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String)->Unit,
    bringIntoViewRequester: BringIntoViewRequester,
    isHiddenByDefault: Boolean = true
){
    var hidden by remember{
        mutableStateOf(isHiddenByDefault)
    }
        AllInTextField(
            modifier = modifier,
            placeholder = placeholder,
            keyboardActions = keyboardActions,
            visualTransformation = if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
            value = value,
            onValueChange = onValueChange,
            bringIntoViewRequester = bringIntoViewRequester,
            trailingContent = {
                IconButton(
                    onClick = { hidden = !hidden }
                ) {
                    Icon(
                        imageVector = if (hidden) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = null,
                        tint = AllInTheme.colors.allIn_LightGrey300
                    )
                }
            },
            keyboardType = keyboardType
        )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun AllInTextFieldPlaceholderPreview() {
    AllInTheme {
        AllInTextField(
            placeholder = "Email",
            value = "",
            onValueChange = { },
            bringIntoViewRequester = BringIntoViewRequester()
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun AllInTextFieldValuePreview() {
    AllInTheme {
        AllInTextField(
            placeholder = "Email",
            value = "JohnDoe@mail.com",
            onValueChange = { },
            bringIntoViewRequester = BringIntoViewRequester()
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
            onValueChange = setValue,
            bringIntoViewRequester = BringIntoViewRequester()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun AllInTextFieldMultilinePreview() {
    AllInTheme {
        AllInTextField(
            placeholder = "David sera il absent le Lundi matin en cours ?",
            value = "",
            onValueChange = { },
            multiLine = true,
            bringIntoViewRequester = BringIntoViewRequester()
        )
    }
}