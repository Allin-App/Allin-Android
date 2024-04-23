package fr.iut.alldev.allin.ui.register.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ext.asPaddingValues
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInGradientButton
import fr.iut.alldev.allin.ui.core.AllInLoading
import fr.iut.alldev.allin.ui.core.AllInPasswordField
import fr.iut.alldev.allin.ui.core.AllInTextField

@Composable
fun RegisterScreenContent(
    navigateToLogin: () -> Unit,
    onRegister: () -> Unit,
    keyboardActions: KeyboardActions,
    loading: Boolean,
    username: String,
    usernameFieldName: String,
    setUsername: (String) -> Unit,
    usernameError: String?,
    email: String,
    emailFieldName: String,
    setEmail: (String) -> Unit,
    emailError: String?,
    passwordFieldName: String,
    password: String,
    passwordError: String?,
    setPassword: (String) -> Unit,
    passwordValidation: String,
    passwordValidationError: String?,
    setPasswordValidation: (String) -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(AllInTheme.themeColors.mainSurface)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            contentPadding = WindowInsets.navigationBars.asPaddingValues(40.dp)
        ) {
            item {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.Hello_x, username),
                    color = AllInTheme.themeColors.onMainSurface,
                    style = AllInTheme.typography.sm1,
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.Register_title),
                    color = AllInTheme.themeColors.onMainSurface,
                    style = AllInTheme.typography.sm1,
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp
                )
                Spacer(modifier = Modifier.height(23.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.Register_subtitle),
                    color = AllInTheme.themeColors.onMainSurface,
                    style = AllInTheme.typography.p1,
                    textAlign = TextAlign.Center,
                    fontSize = 23.sp
                )
                Spacer(modifier = Modifier.height(83.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    AllInTextField(
                        placeholder = usernameFieldName,
                        value = username,
                        modifier = Modifier.fillMaxWidth(),
                        maxChar = 20,
                        onValueChange = setUsername,
                        errorText = usernameError,
                        imeAction = ImeAction.Next,
                        keyboardActions = keyboardActions
                    )
                    AllInTextField(
                        placeholder = emailFieldName,
                        value = email,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = setEmail,
                        errorText = emailError,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next,
                        keyboardActions = keyboardActions
                    )
                    AllInPasswordField(
                        placeholder = passwordFieldName,
                        value = password,
                        modifier = Modifier.fillMaxWidth(),
                        imeAction = ImeAction.Next,
                        keyboardActions = keyboardActions,
                        keyboardType = KeyboardType.Password,
                        errorText = passwordError,
                        onValueChange = setPassword
                    )
                    AllInPasswordField(
                        placeholder = stringResource(id = R.string.confirm_password),
                        value = passwordValidation,
                        modifier = Modifier.fillMaxWidth(),
                        imeAction = ImeAction.Done,
                        keyboardActions = keyboardActions,
                        keyboardType = KeyboardType.Password,
                        errorText = passwordValidationError,
                        onValueChange = setPasswordValidation
                    )
                }
                Spacer(modifier = Modifier.height(120.dp))
            }
        }
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        .2f to Color.Transparent,
                        .5f to AllInTheme.themeColors.background
                    )
                )
                .padding(40.dp)
                .navigationBarsPadding()
        ) {
            AllInGradientButton(
                text = stringResource(id = R.string.Register),
                onClick = onRegister
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.already_have_account),
                    color = AllInTheme.themeColors.onMainSurface,
                    fontSize = 15.sp,
                    style = AllInTheme.typography.p1,
                    modifier = Modifier.padding(end = 5.dp)
                )
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.Login)),
                    style = AllInTheme.typography.p1.copy(
                        color = AllInTheme.colors.allInPurple,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    navigateToLogin()
                }
            }
        }
    }
    AllInLoading(visible = loading)
}

@Preview
@Composable
private fun RegisterScreenContentPreview() {
    AllInTheme {
        RegisterScreenContent(
            navigateToLogin = { },
            onRegister = { },
            keyboardActions = KeyboardActions(),
            loading = false,
            username = "Lekesha",
            usernameFieldName = "Justen",
            setUsername = { },
            usernameError = "Kaitlyn",
            email = "Donnell",
            emailFieldName = "Camron",
            setEmail = { },
            emailError = "Tunisia",
            passwordFieldName = "Cagney",
            password = "Neha",
            passwordError = "December",
            setPassword = { },
            passwordValidation = "Xaviera",
            passwordValidationError = "Keegan",
            setPasswordValidation = { }
        )
    }
}