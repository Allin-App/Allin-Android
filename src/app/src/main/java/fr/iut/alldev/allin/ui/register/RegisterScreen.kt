package fr.iut.alldev.allin.ui.register

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import fr.iut.alldev.allin.ui.register.components.RegisterScreenContent

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

    RegisterScreenContent(
        navigateToLogin = navigateToLogin,
        onRegister = {
            registerViewModel.onRegister(
                usernameFieldName,
                emailFieldName,
                passwordFieldName,
                navigateToDashboard
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

}