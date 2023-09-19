package fr.iut.alldev.allin.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.iut.alldev.allin.ui.home.Home
import fr.iut.alldev.allin.ui.profile.Profile

object Routes {
    const val HOME = "home"
    const val PROFILE = "profile"
    const val BET = "BET"
    const val BET_HISTORY = "BET_HISTORY"
    const val FRIENDS = "FRIENDS"
    const val CURRENT_BETS = "CURRENT_BETS"

}

@Composable
fun AllInNavHost(modifier: Modifier = Modifier,
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
        composable(route = Routes.BET){ Home() }
        composable(route = Routes.BET_HISTORY){ Profile() }
    }
}


