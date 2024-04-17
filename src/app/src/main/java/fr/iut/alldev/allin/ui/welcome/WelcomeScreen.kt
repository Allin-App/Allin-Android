package fr.iut.alldev.allin.ui.welcome

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInButton
import fr.iut.alldev.allin.ui.core.AllInLoading
import fr.iut.alldev.allin.ui.welcome.components.WelcomeScreenContent

@Composable
fun WelcomeScreen(
    navigateToRegister: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToDashboard: () -> Unit,
    viewModel: WelcomeScreenViewModel = hiltViewModel()
) {
    val loading by remember { viewModel.loading }

    LaunchedEffect(viewModel) {
        viewModel.tryAutoLogin(navigateToDashboard)
    }

    WelcomeScreenContent(
        navigateToRegister = navigateToRegister,
        navigateToLogin = navigateToLogin,
        loading = loading
    )
}