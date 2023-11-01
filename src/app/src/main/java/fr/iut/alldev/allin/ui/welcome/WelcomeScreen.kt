package fr.iut.alldev.allin.ui.welcome

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.core.AllInButton
import fr.iut.alldev.allin.theme.AllInTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navigateToRegister: ()->Unit,
    navigateToLogin: ()->Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(.5f)
            .background(AllInTheme.colors.allIn_LoginGradient)
    )
    Box(
        Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    .2f to Color.Transparent,
                    .5f to AllInTheme.themeColors.background
                )
            )
    ){
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(.5f)
                .padding(horizontal = 45.dp)
        ){
            Text(
                text = stringResource(id = R.string.welcome_title),
                color = AllInTheme.themeColors.on_background,
                fontSize = 30.sp,
                style = AllInTheme.typography.h1
            )
            Text(
                text = stringResource(id = R.string.welcome_appname),
                fontSize = 60.sp,
                style = AllInTheme.typography.h1.copy(
                    brush = AllInTheme.colors.allIn_MainGradient
                )
            )
            Spacer(modifier = Modifier.height(43.dp))
            Text(
                text = stringResource(id = R.string.welcome_subtitle),
                color = AllInTheme.themeColors.on_background,
                fontSize = 15.sp,
                style = AllInTheme.typography.r
            )
            Spacer(modifier = Modifier.height(78.dp))
            AllInButton(
                color = AllInTheme.themeColors.tint_1,
                text = stringResource(id = R.string.join),
                textColor = AllInTheme.themeColors.background,
                onClick = navigateToRegister,
                modifier = Modifier.padding(bottom = 13.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.already_have_account),
                    color = AllInTheme.themeColors.tint_1,
                    fontSize = 15.sp,
                    style = AllInTheme.typography.r,
                    modifier = Modifier.padding(end = 5.dp)
                )
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.Login)),
                    style = AllInTheme.typography.r.copy(
                        color = AllInTheme.themeColors.tint_1,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                ){
                    navigateToLogin()
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WelcomeScreenPreview() {
    AllInTheme{
        WelcomeScreen(navigateToRegister = {}, navigateToLogin = {})
    }
}