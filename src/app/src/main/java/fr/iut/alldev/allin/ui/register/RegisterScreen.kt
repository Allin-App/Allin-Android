package fr.iut.alldev.allin.ui.register

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.core.AllInGradientButton
import fr.iut.alldev.allin.ui.core.AllInLoading
import fr.iut.alldev.allin.ui.core.AllInPasswordField
import fr.iut.alldev.allin.ui.core.AllInTextField
import fr.iut.alldev.allin.theme.AllInTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegisterScreen(
    navigateToDashboard: () -> Unit,
    navigateToLogin: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {

    val focusManager = LocalFocusManager.current

    val loading by remember{ registerViewModel.loading }

    val usernameError by remember{ registerViewModel.usernameError }
    val emailError by remember{ registerViewModel.emailError }
    val passwordError by remember{ registerViewModel.passwordError }
    val passwordValidationError by remember{ registerViewModel.passwordValidationError }

    val (username, setUsername) = remember{ registerViewModel.username }
    val (email, setEmail) = remember{ registerViewModel.email }
    val (password, setPassword) = remember{ registerViewModel.password }
    val (passwordValidation, setPasswordValidation) = remember{ registerViewModel.passwordValidation }

    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val scrollState = rememberScrollState()

    val usernameFieldName = stringResource(id = R.string.username)
    val emailFieldName = stringResource(id = R.string.email)
    val passwordFieldName = stringResource(id = R.string.password)

    val keyboardActions = remember {
        KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                registerViewModel.onRegister(
                    usernameFieldName,
                    emailFieldName,
                    passwordFieldName,
                    navigateToDashboard
                )
            },
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(AllInTheme.themeColors.main_surface)
            .padding(horizontal = 44.dp)
    ) {
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.Hello_x, username),
                color = AllInTheme.themeColors.on_main_surface,
                style = AllInTheme.typography.h3,
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.Register_title),
                color = AllInTheme.themeColors.on_main_surface,
                style = AllInTheme.typography.h3,
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )
            Spacer(modifier = Modifier.height(23.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.Register_subtitle),
                color = AllInTheme.themeColors.on_main_surface,
                style = AllInTheme.typography.r,
                textAlign = TextAlign.Center,
                fontSize = 23.sp
            )
            Spacer(modifier = Modifier.height(83.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                AllInTextField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = usernameFieldName,
                    value = username,
                    onValueChange = setUsername,
                    maxChar = 20,
                    errorText = usernameError.errorResource(),
                    bringIntoViewRequester = bringIntoViewRequester,
                    imeAction = ImeAction.Next,
                    keyboardActions = keyboardActions
                )
                AllInTextField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = emailFieldName,
                    value = email,
                    onValueChange = setEmail,
                    errorText = emailError.errorResource(),
                    keyboardType = KeyboardType.Email,
                    bringIntoViewRequester = bringIntoViewRequester,
                    imeAction = ImeAction.Next,
                    keyboardActions = keyboardActions
                )
                AllInPasswordField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = passwordFieldName,
                    value = password,
                    errorText = passwordError.errorResource(),
                    onValueChange = setPassword,
                    bringIntoViewRequester = bringIntoViewRequester,
                    imeAction = ImeAction.Next,
                    keyboardActions = keyboardActions
                )
                AllInPasswordField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(id = R.string.confirm_password),
                    value = passwordValidation,
                    errorText = passwordValidationError.errorResource(),
                    onValueChange = setPasswordValidation,
                    bringIntoViewRequester = bringIntoViewRequester,
                    imeAction = ImeAction.Done,
                    keyboardActions = keyboardActions
                )
            }
        }
        Column(
            Modifier
                .padding(bottom = 32.dp)
        ) {
            AllInGradientButton(
                text = stringResource(id = R.string.Register),
                onClick = {
                    registerViewModel.onRegister(
                        usernameFieldName,
                        emailFieldName,
                        passwordFieldName,
                        navigateToDashboard
                    )
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.already_have_account),
                    color = AllInTheme.themeColors.on_main_surface,
                    fontSize = 15.sp,
                    style = AllInTheme.typography.r,
                    modifier = Modifier.padding(end = 5.dp)
                )
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.Login)),
                    style = AllInTheme.typography.r.copy(
                        color = AllInTheme.colors.allIn_Purple,
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