package fr.iut.alldev.allin.ui.login

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
import androidx.compose.ui.text.style.TextAlign
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
fun LoginScreen(
    navigateToDashboard: ()->Unit,
    navigateToRegister: ()->Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val bringIntoViewRequester = BringIntoViewRequester()
    val loading by remember{ loginViewModel.loading }

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
                text = stringResource(id = R.string.Login_title),
                color = AllInTheme.themeColors.on_main_surface,
                style = AllInTheme.typography.h3,
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )
            Spacer(modifier = Modifier.height(23.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.Login_subtitle),
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
                    value = "",
                    onValueChange = { },
                    bringIntoViewRequester = bringIntoViewRequester
                )
                AllInPasswordField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(id = R.string.password),
                    value = "",
                    onValueChange = { },
                    bringIntoViewRequester = bringIntoViewRequester
                )
            }
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.forgot_password)),
                style = AllInTheme.typography.m.copy(
                    color = AllInTheme.themeColors.on_main_surface,
                    fontSize = 15.sp,
                ),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 15.dp)
            ){
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
                    color = AllInTheme.themeColors.on_main_surface,
                    fontSize = 15.sp,
                    style = AllInTheme.typography.r,
                    modifier = Modifier.padding(end = 5.dp)
                )
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.Register)),
                    style = AllInTheme.typography.r.copy(
                        color = AllInTheme.colors.allIn_Purple,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    navigateToRegister()
                }
            }
        }
    }
    AnimatedVisibility(visible = loading) {
        AllInLoading()
    }
}