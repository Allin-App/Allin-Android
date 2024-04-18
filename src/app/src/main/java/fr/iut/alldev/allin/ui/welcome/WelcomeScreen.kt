package fr.iut.alldev.allin.ui.welcome

import androidx.compose.runtime.Composable
import fr.iut.alldev.allin.ui.welcome.components.WelcomeScreenContent

@Composable
fun WelcomeScreen(
    navigateToRegister: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    WelcomeScreenContent(
        navigateToRegister = navigateToRegister,
        navigateToLogin = navigateToLogin
    )
}