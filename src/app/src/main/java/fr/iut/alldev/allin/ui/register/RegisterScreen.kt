package fr.iut.alldev.allin.ui.register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
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
import fr.iut.alldev.allin.ui.theme.AllInTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegisterScreen(
    navigateToDashboard: () -> Unit,
    navigateToLogin: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {
    val loading by remember{ registerViewModel.loading }

    val (username, setUsername) = remember{ registerViewModel.username }
    val (email, setEmail) = remember{ registerViewModel.email }
    val (password, setPassword) = remember{ registerViewModel.password }
    val (passwordValidation, setPasswordValidation) = remember{ registerViewModel.passwordValidation }

    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    Box(
        Modifier
            .fillMaxSize()
            .background(AllInTheme.themeColors.main_surface)
            .padding(horizontal = 44.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            Modifier.align(Alignment.Center)
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
                    placeholder = stringResource(id = R.string.username),
                    value = username,
                    onValueChange = setUsername,
                    maxChar = 20,
                    bringIntoViewRequester = bringIntoViewRequester
                )
                AllInTextField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(id = R.string.email),
                    value = email,
                    onValueChange = setEmail,
                    keyboardType = KeyboardType.Email,
                    bringIntoViewRequester = bringIntoViewRequester
                )
                AllInPasswordField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(id = R.string.password),
                    value = password,
                    onValueChange = setPassword,
                    bringIntoViewRequester = bringIntoViewRequester
                )
                AllInPasswordField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(id = R.string.confirm_password),
                    value = passwordValidation,
                    onValueChange = setPasswordValidation,
                    bringIntoViewRequester = bringIntoViewRequester
                )
            }
            Spacer(modifier = Modifier.height(67.dp))
        }
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        ) {
            AllInGradientButton(
                text = stringResource(id = R.string.Register),
                onClick = {
                    registerViewModel.onRegister(navigateToDashboard)
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
    AnimatedVisibility(visible = loading) {
        AllInLoading()
    }
}