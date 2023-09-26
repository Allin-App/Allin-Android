package fr.iut.alldev.allin.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.iut.alldev.allin.ui.bet.BetScreen
import fr.iut.alldev.allin.ui.login.LoginScreen
import fr.iut.alldev.allin.ui.navigation.drawer.AllInDrawer
import fr.iut.alldev.allin.ui.profile.Profile
import fr.iut.alldev.allin.ui.register.RegisterScreen
import fr.iut.alldev.allin.ui.theme.AllInTheme
import fr.iut.alldev.allin.ui.welcome.WelcomeScreen

object Routes {
    const val WELCOME = "WELCOME"
    const val REGISTER = "REGISTER"
    const val LOGIN = "LOGIN"
    const val BET = "BET"
    const val BET_HISTORY = "BET_HISTORY"
    const val FRIENDS = "FRIENDS"
    const val CURRENT_BETS = "CURRENT_BETS"
    const val DASHBOARD = "DASHBOARD"

}

private fun NavHostController.popUpTo(route: String, baseRoute: String){
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
fun AllInNavHost(modifier: Modifier = Modifier,
                 navController: NavHostController = rememberNavController(),
                 startDestination: String = Routes.WELCOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition =
        {
            if(navController.currentDestination?.route != Routes.DASHBOARD)
                slideInHorizontally(initialOffsetX = { it })
            else
                fadeIn(animationSpec = tween(1500))
        },
        exitTransition =
        {
            if(navController.currentDestination?.route != Routes.DASHBOARD)
                slideOutHorizontally(targetOffsetX = { -it/2 })
            else
                fadeOut(
                    animationSpec = tween(1500)
                )

        },
        modifier = modifier.fillMaxSize().background(AllInTheme.themeColors.main_surface),
    ) {

        composable(route = Routes.WELCOME){
            WelcomeScreen(
                onClickJoin = {
                    navController.popUpTo(Routes.REGISTER, Routes.WELCOME)
                },
                onClickLogin = {
                    navController.popUpTo(Routes.LOGIN, Routes.WELCOME)
                }
            )
        }
        composable(route = Routes.REGISTER){
            RegisterScreen(
                onClickRegister = {
                    navController.popUpTo(Routes.DASHBOARD, Routes.REGISTER)
                },
                onClickLogin = {
                    navController.popUpTo(Routes.LOGIN, Routes.REGISTER)
                }
            )
        }
        composable(route = Routes.LOGIN){
            LoginScreen(
                onClickRegister = {
                    navController.popUpTo(Routes.REGISTER, Routes.LOGIN)
                },
                onClickLogin = {
                    navController.popUpTo(Routes.DASHBOARD, Routes.LOGIN)
                }
            )
        }
        composable(
            route = Routes.DASHBOARD,
        ){
            AllInDrawer()
        }
    }
}
@Composable
fun AllInDashboard(
     modifier: Modifier = Modifier,
     navController: NavHostController = rememberNavController(),
     startDestination: String = Routes.BET
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier.fillMaxSize(),
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(route = Routes.BET) { BetScreen() }
        composable(route = Routes.BET_HISTORY) { Profile() }
    }
}