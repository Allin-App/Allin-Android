package fr.iut.alldev.allin.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.bet.BetScreen
import fr.iut.alldev.allin.ui.betCreation.BetCreationScreen
import fr.iut.alldev.allin.ui.betHistory.BetHistoryScreen
import fr.iut.alldev.allin.ui.login.LoginScreen
import fr.iut.alldev.allin.ui.main.MainScreen
import fr.iut.alldev.allin.ui.main.MainViewModel
import fr.iut.alldev.allin.ui.register.RegisterScreen
import fr.iut.alldev.allin.ui.welcome.WelcomeScreen

object Routes {
    const val WELCOME = "WELCOME"
    const val REGISTER = "REGISTER"
    const val LOGIN = "LOGIN"
    const val DASHBOARD = "DASHBOARD"
    const val PUBLIC_BETS = "PUBLIC_BETS"
    const val BET_CREATION = "BET_CREATION"
    const val BET_HISTORY = "BET_HISTORY"
    const val FRIENDS = "FRIENDS"

}

object NavArguments {
    const val ARG_BET_HISTORY_IS_CURRENT = "ARG_BET_HISTORY_IS_CURRENT"
}


internal fun NavHostController.popUpTo(route: String, baseRoute: String) {
    this.navigate(route) {
        launchSingleTop = true
        popUpTo(baseRoute) {
            saveState = true
            inclusive = true
        }
        restoreState = true
    }
}

@Composable
fun AllInNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.WELCOME,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition =
        {
            if (navController.currentDestination?.route != Routes.DASHBOARD)
                slideInHorizontally(initialOffsetX = { it })
            else
                fadeIn(animationSpec = tween(1500))
        },
        exitTransition =
        {
            if (navController.currentDestination?.route != Routes.DASHBOARD)
                slideOutHorizontally(targetOffsetX = { -it / 2 })
            else
                fadeOut(
                    animationSpec = tween(1500)
                )
        },
        modifier = modifier
            .fillMaxSize()
            .background(AllInTheme.themeColors.mainSurface),
    ) {
        allInWelcomeScreen(navController)
        allInRegisterScreen(navController)
        allInLoginScreen(navController)
        allInDashboard()
    }
}

@Composable
internal fun AllInDrawerNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mainViewModel: MainViewModel,
    selectBet: (Bet, Boolean) -> Unit,
    startDestination: String = Routes.PUBLIC_BETS,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier.fillMaxSize(),
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(route = Routes.PUBLIC_BETS) {
            BetScreen(
                selectBet = selectBet
            )
        }
        composable(route = Routes.BET_CREATION) {
            BetCreationScreen(
                mainViewModel = mainViewModel
            )
        }

        composable(
            route = "${Routes.BET_HISTORY}/{${NavArguments.ARG_BET_HISTORY_IS_CURRENT}}",
            arguments = listOf(
                navArgument(NavArguments.ARG_BET_HISTORY_IS_CURRENT) {
                    type = NavType.BoolType
                }
            )

        ) {
            val isCurrent =
                it.arguments?.getBoolean(NavArguments.ARG_BET_HISTORY_IS_CURRENT) ?: false
            BetHistoryScreen(
                isCurrent = isCurrent,
                viewModel = hiltViewModel(it, isCurrent.toString())
            )
        }
    }
}

private fun NavGraphBuilder.allInWelcomeScreen(
    navController: NavHostController,
) {
    composable(route = Routes.WELCOME) {
        WelcomeScreen(
            navigateToRegister = {
                navController.popUpTo(Routes.REGISTER, Routes.WELCOME)
            },
            navigateToLogin = {
                navController.popUpTo(Routes.LOGIN, Routes.WELCOME)
            }
        )
    }
}

private fun NavGraphBuilder.allInRegisterScreen(
    navController: NavHostController,
) {
    composable(route = Routes.REGISTER) {
        RegisterScreen(
            navigateToDashboard = {
                navController.popUpTo(Routes.DASHBOARD, Routes.REGISTER)
            },
            navigateToLogin = {
                navController.popUpTo(Routes.LOGIN, Routes.REGISTER)
            }
        )
    }
}

private fun NavGraphBuilder.allInLoginScreen(
    navController: NavHostController,
) {
    composable(route = Routes.LOGIN) {
        LoginScreen(
            navigateToRegister = {
                navController.popUpTo(Routes.REGISTER, Routes.LOGIN)
            },
            navigateToDashboard = {
                navController.popUpTo(Routes.DASHBOARD, Routes.LOGIN)
            }
        )
    }
}

private fun NavGraphBuilder.allInDashboard() {
    composable(
        route = Routes.DASHBOARD,
    ) {
        MainScreen()
    }
}