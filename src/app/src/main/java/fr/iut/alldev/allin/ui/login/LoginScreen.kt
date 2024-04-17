package fr.iut.alldev.allin.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInAlertDialog
import fr.iut.alldev.allin.ui.core.AllInGradientButton
import fr.iut.alldev.allin.ui.core.AllInLoading
import fr.iut.alldev.allin.ui.core.AllInPasswordField
import fr.iut.alldev.allin.ui.core.AllInTextField

@Composable
fun LoginScreen(
    navigateToDashboard: () -> Unit,
    navigateToRegister: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val focusManager = LocalFocusManager.current

    val loading by remember { loginViewModel.loading }
    var hasLoginError by remember { loginViewModel.hasError }

    val (username, setUsername) = remember { loginViewModel.username }
    val (password, setPassword) = remember { loginViewModel.password }

    val keyboardActions = remember {
        KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                loginViewModel.onLogin(navigateToDashboard)
            },
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(AllInTheme.themeColors.mainSurface)
            .padding(horizontal = 44.dp)
            .safeContentPadding()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            Modifier.align(Alignment.Center)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.Login_title),
                color = AllInTheme.themeColors.onMainSurface,
                style = AllInTheme.typography.sm1,
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )
            Spacer(modifier = Modifier.height(23.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.Login_subtitle),
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
                    placeholder = stringResource(id = R.string.username),
                    value = username,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = setUsername,
                    imeAction = ImeAction.Next,
                    keyboardActions = keyboardActions
                )
                AllInPasswordField(
                    placeholder = stringResource(id = R.string.password),
                    value = password,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    keyboardActions = keyboardActions,
                    onValueChange = setPassword
                )
            }
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.forgot_password)),
                style = AllInTheme.typography.sm2.copy(
                    color = AllInTheme.themeColors.onMainSurface,
                    fontSize = 15.sp,
                ),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 15.dp)
            ) {
                // TODO : Forgot password
            }
            Spacer(modifier = Modifier.height(67.dp))
        }
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        ) {
            AllInGradientButton(
                text = stringResource(id = R.string.Login),
                onClick = {
                    loginViewModel.onLogin(navigateToDashboard)
                },
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.no_account),
                    color = AllInTheme.themeColors.onMainSurface,
                    fontSize = 15.sp,
                    style = AllInTheme.typography.p1,
                    modifier = Modifier.padding(end = 5.dp)
                )
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.Register)),
                    style = AllInTheme.typography.p1.copy(
                        color = AllInTheme.colors.allInPurple,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    navigateToRegister()
                }
            }
        }
    }
    AllInLoading(visible = loading)
    AllInAlertDialog(
        enabled = hasLoginError,
        title = stringResource(id = R.string.Login_Error_Title),
        text = stringResource(id = R.string.Login_Error_Content),
        onDismiss = { hasLoginError = false }
    )
}