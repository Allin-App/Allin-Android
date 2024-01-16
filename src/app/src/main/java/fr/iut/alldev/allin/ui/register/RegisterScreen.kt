package fr.iut.alldev.allin.ui.register

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInGradientButton
import fr.iut.alldev.allin.ui.core.AllInLoading
import fr.iut.alldev.allin.ui.core.AllInPasswordField
import fr.iut.alldev.allin.ui.core.AllInTextField

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegisterScreen(
    navigateToDashboard: () -> Unit,
    navigateToLogin: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {

    val focusManager = LocalFocusManager.current

    val loading by remember { registerViewModel.loading }

    val usernameError by remember { registerViewModel.usernameError }
    val emailError by remember { registerViewModel.emailError }
    val passwordError by remember { registerViewModel.passwordError }
    val passwordValidationError by remember { registerViewModel.passwordValidationError }

    val (username, setUsername) = remember { registerViewModel.username }
    val (email, setEmail) = remember { registerViewModel.email }
    val (password, setPassword) = remember { registerViewModel.password }
    val (passwordValidation, setPasswordValidation) = remember { registerViewModel.passwordValidation }

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
            .background(AllInTheme.themeColors.mainSurface)
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
                    errorText = usernameError.errorResource(),
                    imeAction = ImeAction.Next,
                    keyboardActions = keyboardActions
                )
                AllInTextField(
                    placeholder = emailFieldName,
                    value = email,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = setEmail,
                    errorText = emailError.errorResource(),
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
                    errorText = passwordError.errorResource(),
                    onValueChange = setPassword
                )
                AllInPasswordField(
                    placeholder = stringResource(id = R.string.confirm_password),
                    value = passwordValidation,
                    modifier = Modifier.fillMaxWidth(),
                    imeAction = ImeAction.Done,
                    keyboardActions = keyboardActions,
                    errorText = passwordValidationError.errorResource(),
                    onValueChange = setPasswordValidation
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