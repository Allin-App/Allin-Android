package fr.iut.alldev.allin.ui.register

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.core.AllInAlertDialog
import fr.iut.alldev.allin.ui.register.components.RegisterScreenContent

@Composable
fun RegisterScreen(
    navigateToDashboard: () -> Unit,
    navigateToLogin: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {

    val focusManager = LocalFocusManager.current

    val loading by remember { registerViewModel.loading }

    var hasNetworkError by remember { mutableStateOf(false) }
    val usernameError by remember { registerViewModel.usernameError }
    val emailError by remember { registerViewModel.emailError }
    val passwordError by remember { registerViewModel.passwordError }
    val passwordValidationError by remember { registerViewModel.passwordValidationError }

    val (username, setUsername) = remember { registerViewModel.username }
    val (email, setEmail) = remember { registerViewModel.email }
    val (password, setPassword) = remember { registerViewModel.password }
    val (passwordValidation, setPasswordValidation) = remember { registerViewModel.passwordValidation }

    val usernameFieldName = stringResource(id = R.string.generic_username)
    val emailFieldName = stringResource(id = R.string.generic_email)
    val passwordFieldName = stringResource(id = R.string.generic_password)

    val keyboardActions = remember {
        KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                registerViewModel.onRegister(
                    usernameFieldName = usernameFieldName,
                    emailFieldName = emailFieldName,
                    passwordFieldName = passwordFieldName,
                    navigateToDashboard = navigateToDashboard,
                    displayNetworkErrorAlert = { hasNetworkError = false }
                )
            },
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    }

    RegisterScreenContent(
        navigateToLogin = navigateToLogin,
        onRegister = {
            registerViewModel.onRegister(
                usernameFieldName = usernameFieldName,
                emailFieldName = emailFieldName,
                passwordFieldName = passwordFieldName,
                navigateToDashboard = navigateToDashboard,
                displayNetworkErrorAlert = { hasNetworkError = false }
            )
        },
        keyboardActions = keyboardActions,
        loading = loading,
        username = username,
        usernameFieldName = usernameFieldName,
        setUsername = setUsername,
        usernameError = usernameError.errorResource(),
        email = email,
        emailFieldName = emailFieldName,
        setEmail = setEmail,
        emailError = emailError.errorResource(),
        passwordFieldName = passwordFieldName,
        password = password,
        passwordError = passwordError.errorResource(),
        setPassword = setPassword,
        passwordValidation = passwordValidation,
        passwordValidationError = passwordValidationError.errorResource(),
        setPasswordValidation = setPasswordValidation
    )

    AllInAlertDialog(
        enabled = hasNetworkError,
        title = stringResource(id = R.string.network_error),
        text = stringResource(id = R.string.network_error_text),
        onDismiss = { hasNetworkError = false }
    )

}