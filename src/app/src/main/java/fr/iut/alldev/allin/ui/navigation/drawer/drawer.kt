package fr.iut.alldev.allin.ui.navigation.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.core.topbar.AllInTopBar
import fr.iut.alldev.allin.ui.navigation.AllInNavHost
import fr.iut.alldev.allin.ui.navigation.Routes
import fr.iut.alldev.allin.ui.theme.AllInTheme
import kotlinx.coroutines.launch
import kotlin.math.abs


sealed class TopLevelDestination(
    val route: String,
    val title: Int,
    val subtitle: Int,
    val emoji: Int
) {
    object BET : TopLevelDestination(
        route = Routes.BET,
        title = R.string.create_a_bet,
        subtitle = R.string.create_a_bet_subtitle,
        emoji = R.drawable.video_game
    )
    object BET_HISTORY : TopLevelDestination(
        route = Routes.BET_HISTORY,
        title = R.string.bet_history,
        subtitle = R.string.bet_history_subtitle,
        emoji = R.drawable.eyes
    )
    object FRIENDS : TopLevelDestination(
        route = Routes.FRIENDS,
        title = R.string.friends,
        subtitle = R.string.friends_subtitle,
        emoji = R.drawable.holding_hands
    )
    object CURRENT_BETS : TopLevelDestination(
        route = Routes.CURRENT_BETS,
        title = R.string.current_bets,
        subtitle = R.string.current_bets_subtitle,
        emoji = R.drawable.money_with_wings
    )
}

val topLevelDestinations = listOf(
    TopLevelDestination.BET,
    TopLevelDestination.BET_HISTORY,
    TopLevelDestination.FRIENDS,
    TopLevelDestination.CURRENT_BETS
)

@Composable
fun AllInDrawer(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = Routes.BET

) {
    val scope = rememberCoroutineScope()
    val drawerOffset = derivedStateOf { drawerState.offset.value }
    var drawerWidth by remember {
        mutableStateOf(drawerState.offset.value)
    }
    LaunchedEffect(drawerWidth == 0f) {
        drawerWidth = drawerState.offset.value
    }

    val localDensity = LocalDensity.current
    val contentOffset by derivedStateOf {
        if (drawerWidth == 0f) 0.dp
        else with(localDensity) {
            (abs(drawerWidth) + drawerOffset.value).toDp()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape,
                drawerContainerColor = AllInTheme.colors.allIn_Dark
            ) {
                DrawerHeader(
                    nbBets = 135,
                    bestWin = 365,
                    nbFriends = 5,
                    username = "Pseudo",
                    modifier = Modifier.padding(top = 39.dp, bottom = 26.dp)

                )
                topLevelDestinations.forEach { item ->
                    DrawerCell(
                        title = stringResource(item.title).uppercase(),
                        subtitle = stringResource(item.subtitle),
                        emoji = painterResource(id = item.emoji),
                        onClick = { scope.launch { drawerState.close() }
                                    navController.navigate(item.route){
                                        launchSingleTop = true
                                        popUpTo(
                                            startDestination
                                        ) {
                                            saveState = true
                                            inclusive = true
                                        }
                                        restoreState = true
                                    }
                                  },
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 13.dp)
                    )
                }
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(start = 19.dp, bottom = 10.dp)) {
                    IconButton(onClick = { /*TODO*/ },
                        modifier = Modifier.align(Alignment.BottomStart)){
                        Icon(imageVector = Icons.Filled.Settings,
                            modifier = Modifier.size(40.dp),
                            tint = AllInTheme.colors.allIn_DarkGrey50,
                            contentDescription = null)
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.offset( x = contentOffset),
            topBar = { AllInTopBar(onMenuClicked = { scope.launch { drawerState.open() } }, coinAmount = 541) }// TODO: CoinAmount
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(AllInTheme.colors.allIn_White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AllInNavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = startDestination
                )
            }
        }
    }
}



