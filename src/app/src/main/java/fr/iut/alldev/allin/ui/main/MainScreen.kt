package fr.iut.alldev.allin.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.betStatus.BetStatusBottomSheet
import fr.iut.alldev.allin.ui.betStatus.vo.BetStatusBottomSheetBetDisplayer
import fr.iut.alldev.allin.ui.core.AllInLoading
import fr.iut.alldev.allin.ui.core.snackbar.AllInSnackbarVisualsImpl
import fr.iut.alldev.allin.ui.main.components.AllInScaffold
import fr.iut.alldev.allin.ui.main.event.DailyReward
import fr.iut.alldev.allin.ui.main.event.ToConfirmBet
import fr.iut.alldev.allin.ui.main.event.WonBet
import fr.iut.alldev.allin.ui.navigation.AllInDrawerNavHost
import fr.iut.alldev.allin.ui.navigation.Routes
import fr.iut.alldev.allin.ui.navigation.TopLevelDestination
import fr.iut.alldev.allin.ui.navigation.drawer.AllInDrawer
import fr.iut.alldev.allin.ui.navigation.popUpTo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

private const val EVENT_DISMISS_DELAY_MS = 150L

private val topLevelDestinations = listOf(
    TopLevelDestination.BetCreation,
    TopLevelDestination.PublicBets,
    TopLevelDestination.CurrentBets,
    TopLevelDestination.BetHistory,
    TopLevelDestination.Friends,
    TopLevelDestination.Ranking,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.PUBLIC_BETS,
    mainViewModel: MainViewModel = hiltViewModel(),
    navigateToWelcomeScreen: () -> Unit
) {
    val scope = rememberCoroutineScope()

    val focusManager = LocalFocusManager.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    LaunchedEffect(key1 = drawerState.targetValue) {
        focusManager.clearFocus()
    }

    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarContent by remember { mainViewModel.snackbarContent }

    LaunchedEffect(snackbarContent) {
        snackbarContent?.let {
            scope.launch {
                snackbarHostState.currentSnackbarData?.dismiss()
                snackbarHostState.showSnackbar(
                    AllInSnackbarVisualsImpl(
                        message = it.text,
                        withDismissAction = false,
                        duration = SnackbarDuration.Short,
                        type = it.type
                    )
                )
                snackbarHostState.currentSnackbarData
                snackbarContent = null
            }
        }
    }

    val (loading, setLoading) = remember { mainViewModel.loading }
    val currentUser by mainViewModel.currentUser.collectAsStateWithLifecycle()

    val selectedBet by remember { mainViewModel.selectedBet }
    var statusVisibility by remember { mutableStateOf(false) }
    var statusVisibilityConfirm by remember { mutableStateOf<SheetValue?>(null) }
    val (participateSheetVisibility, setParticipateSheetVisibility) = remember { mutableStateOf(false) }
    val statusBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = {
            statusVisibilityConfirm = it
            true
        }
    )
    val betStatusDisplayer = remember {
        BetStatusBottomSheetBetDisplayer(
            openParticipateSheet = { setParticipateSheetVisibility(true) }
        )
    }

    val events = remember { mainViewModel.events }
    val eventBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    AllInDrawer(
        drawerState = drawerState,
        destinations = topLevelDestinations,
        scope = scope,
        username = currentUser?.username ?: "",
        nbFriends = currentUser?.nbFriends ?: 0,
        nbBets = currentUser?.nbBets ?: 0,
        bestWin = currentUser?.bestWin ?: 0,
        image = currentUser?.image,
        navigateTo = { route ->
            mainViewModel.fetchEvents()
            navController.popUpTo(route, startDestination)
        },
        logout = {
            mainViewModel.onLogout()
            navigateToWelcomeScreen()
        }
    ) {
        AllInScaffold(
            onMenuClicked = { scope.launch { drawerState.open() } },
            coinAmount = currentUser?.coins ?: 0,
            drawerState = drawerState,
            snackbarHostState = snackbarHostState
        ) {
            Column(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize()
                    .background(AllInTheme.colors.mainSurface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AllInDrawerNavHost(
                    navController = navController,
                    selectBet = { bet, participate ->
                        mainViewModel.openBetDetail(bet) { detail ->
                            statusVisibility = true
                            if (
                                detail.bet.betStatus == BetStatus.IN_PROGRESS &&
                                detail.userParticipation == null &&
                                detail.bet.creator != currentUser?.username
                            ) {
                                setParticipateSheetVisibility(participate)
                            }
                        }
                    },
                    setLoading = setLoading,
                    putSnackbarContent = { mainViewModel.putSnackbarContent(it) }
                ) {
                    BackHandler(enabled = drawerState.isOpen) {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                }
            }
        }
    }

    events.firstOrNull()?.let {
        when (val event = it) {
            is ToConfirmBet -> {
                Timber.d("ToConfirmBet")
                scope.launch { eventBottomSheetState.show() }
                event.Display(sheetState = eventBottomSheetState) {
                    mainViewModel.dismissedEvents += it
                    scope.launch {
                        eventBottomSheetState.hide()
                        delay(EVENT_DISMISS_DELAY_MS)
                        events.removeFirstOrNull()
                    }
                }
            }

            is WonBet -> {
                Timber.d("WonBet")
                scope.launch { eventBottomSheetState.show() }
                event.Display(sheetState = eventBottomSheetState) {
                    mainViewModel.dismissedEvents += it
                    scope.launch {
                        eventBottomSheetState.hide()
                        delay(EVENT_DISMISS_DELAY_MS)
                        mainViewModel.increaseCoins(event.betResult.amount)
                        events.removeFirstOrNull()
                    }
                }
            }

            is DailyReward -> {
                var dailyRewardVisible by remember { mutableStateOf(true) }
                AnimatedVisibility(
                    visible = dailyRewardVisible,
                    enter = fadeIn(tween(EVENT_DISMISS_DELAY_MS.toInt())),
                    exit = fadeOut(tween(EVENT_DISMISS_DELAY_MS.toInt()))
                ) {
                    (events.firstOrNull() as? DailyReward)?.let {
                        it.Display(
                            onDismiss = {
                                mainViewModel.increaseCoins(it.amount)
                                dailyRewardVisible = false
                                mainViewModel.dismissedEvents += it
                                scope.launch {
                                    delay(EVENT_DISMISS_DELAY_MS)
                                    events.removeFirstOrNull()
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    BetStatusBottomSheet(
        state = statusBottomSheetState,
        sheetVisibility = statusVisibility,
        sheetBackVisibility = statusBottomSheetState.targetValue == SheetValue.Expanded || statusVisibilityConfirm == SheetValue.Expanded,
        onDismiss = { statusVisibility = false },
        betDetail = selectedBet,
        displayBet = { detail ->
            currentUser?.let { user -> betStatusDisplayer.DisplayBet(betDetail = detail, currentUser = user) }
        },
        userCoinAmount = currentUser?.coins ?: 0,
        onParticipate = { stake, response -> mainViewModel.participateToBet(stake, response) },
        participateSheetVisibility = participateSheetVisibility,
        setParticipateSheetVisibility = setParticipateSheetVisibility
    )

    AllInLoading(visible = loading)
}



