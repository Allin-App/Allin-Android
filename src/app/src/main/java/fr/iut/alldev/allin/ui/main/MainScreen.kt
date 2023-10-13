package fr.iut.alldev.allin.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.iut.alldev.allin.data.model.BetStatus
import fr.iut.alldev.allin.ui.betstatus.BetStatusBottomSheet
import fr.iut.alldev.allin.ui.main.components.AllInScaffold
import fr.iut.alldev.allin.ui.navigation.AllInDrawerNavHost
import fr.iut.alldev.allin.ui.navigation.Routes
import fr.iut.alldev.allin.ui.navigation.TopLevelDestination
import fr.iut.alldev.allin.ui.navigation.drawer.AllInDrawer
import fr.iut.alldev.allin.ui.navigation.popUpTo
import fr.iut.alldev.allin.ui.theme.AllInTheme
import kotlinx.coroutines.launch

private val topLevelDestinations = listOf(
    TopLevelDestination.PUBLIC_BETS,
    TopLevelDestination.BET_CREATION,
    TopLevelDestination.BET_HISTORY,
    TopLevelDestination.FRIENDS,
    TopLevelDestination.CURRENT_BETS
)
@Composable
private fun rememberBetStatusVisibilities(): Triple<MutableState<Boolean>, MutableState<Boolean>, (Boolean) -> Unit> {
    val statusVisibility = remember {
        mutableStateOf(false)
    }

    val sheetBackVisibility = remember {
        mutableStateOf(false)
    }

    val setStatusVisibility = {
            it: Boolean ->
        statusVisibility.value = it
        if(it) sheetBackVisibility.value = true
    }
    return Triple(statusVisibility, sheetBackVisibility, setStatusVisibility)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = Routes.PUBLIC_BETS,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val currentUser = remember{
        mainViewModel.currentUser
    }

    val scope = rememberCoroutineScope()

    val (statusVisibility, sheetBackVisibility, setStatusVisibility)
    = rememberBetStatusVisibilities()

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = {
            if(it == SheetValue.Hidden){
                sheetBackVisibility.value = false
            }
            true
        }
    )

    AllInDrawer(
        drawerState = drawerState,
        destinations = topLevelDestinations,
        scope = scope,
        username = currentUser.username,
        nbFriends = 5,
        nbBets = 35,
        bestWin = 362,
        navigateTo = {route ->
            navController.popUpTo(route, startDestination)
        }
    ){
        AllInScaffold(
            onMenuClicked = {  scope.launch { drawerState.open() } },
            coinAmount = currentUser.coins,
            drawerState = drawerState
        ) {
            Column(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize()
                    .background(AllInTheme.themeColors.main_surface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AllInDrawerNavHost(
                    navController = navController,
                    setStatusVisibility = setStatusVisibility
                )
            }
        }
    }

    BetStatusBottomSheet(
        state = bottomSheetState,
        sheetVisibility = statusVisibility.value,
        sheetBackVisibility = sheetBackVisibility.value,
        onDismiss = {
            setStatusVisibility(false)
        },
        betStatus = BetStatus.IN_PROGRESS
    )

    BackHandler(
        enabled = drawerState.isOpen
    ) {
        scope.launch {
            drawerState.close()
        }
    }

    BackHandler(
        enabled = statusVisibility.value
    ) {
        scope.launch {
            setStatusVisibility(false)
        }
    }
}



