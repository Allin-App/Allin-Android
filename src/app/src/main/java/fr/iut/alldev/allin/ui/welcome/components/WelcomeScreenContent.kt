package fr.iut.alldev.allin.ui.welcome.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentHeight
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
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInButton
import fr.iut.alldev.allin.ui.core.AllInLoading

@Composable
fun WelcomeScreenContent(
    navigateToRegister: () -> Unit,
    navigateToLogin: () -> Unit,
    loading: Boolean
) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(.5f)
            .background(AllInTheme.colors.allInLoginGradient)
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
    ) {
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(40.dp)
                .navigationBarsPadding()
        ) {
            Text(
                text = stringResource(id = R.string.welcome_title),
                color = AllInTheme.themeColors.onBackground,
                fontSize = 30.sp,
                style = AllInTheme.typography.h1
            )
            Text(
                text = stringResource(id = R.string.welcome_appname),
                fontSize = 60.sp,
                style = AllInTheme.typography.h1.copy(
                    brush = AllInTheme.colors.allInMainGradient
                )
            )
            Spacer(modifier = Modifier.height(43.dp))
            Text(
                text = stringResource(id = R.string.welcome_subtitle),
                color = AllInTheme.themeColors.onBackground,
                fontSize = 15.sp,
                style = AllInTheme.typography.p1
            )
            Spacer(modifier = Modifier.height(78.dp))

            AllInButton(
                color = AllInTheme.themeColors.tint1,
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
                    color = AllInTheme.themeColors.tint1,
                    fontSize = 15.sp,
                    style = AllInTheme.typography.p1,
                    modifier = Modifier.padding(end = 5.dp)
                )
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.Login)),
                    style = AllInTheme.typography.p1.copy(
                        color = AllInTheme.themeColors.tint1,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    onClick = { navigateToLogin() }
                )
            }
        }
    }

    AllInLoading(visible = loading)
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WelcomeScreenContentPreview() {
    AllInTheme {
        WelcomeScreenContent(
            navigateToRegister = { },
            navigateToLogin = { },
            loading = false
        )
    }
}