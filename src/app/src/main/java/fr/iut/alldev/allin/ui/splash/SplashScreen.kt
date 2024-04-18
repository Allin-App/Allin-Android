package fr.iut.alldev.allin.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.iut.alldev.allin.ui.splash.components.SplashScreenContent

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = hiltViewModel(),
    navigateToWelcomeScreen: () -> Unit,
    navigateToDashboard: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state) {
        (state as? SplashScreenViewModel.State.Loaded)?.let { loadedState ->
            if (loadedState.isLoggedIn) {
                navigateToDashboard()
            } else {
                navigateToWelcomeScreen()
            }
        }
    }

    SplashScreenContent()
}

