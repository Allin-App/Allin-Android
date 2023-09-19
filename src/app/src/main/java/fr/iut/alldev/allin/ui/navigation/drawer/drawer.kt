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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.iut.alldev.allin.ui.core.topbar.AllInTopBar
import fr.iut.alldev.allin.ui.core.ProfilePicture
import fr.iut.alldev.allin.ui.navigation.AllInNavHost
import fr.iut.alldev.allin.ui.navigation.Routes
import fr.iut.alldev.allin.ui.theme.AllInTheme
import kotlinx.coroutines.launch
import kotlin.math.abs


sealed class TopLevelDestination(
    val route: String,
    val title: String,
    val subtitle: String,
) {
    object BET : TopLevelDestination(route = Routes.BET, title = "CREER UN BET", subtitle = "Créez un nouveau BET et faites participer vos amis.")
    object BET_HISTORY : TopLevelDestination(route = Routes.BET_HISTORY, title = "HISTORIQUE DES BETS", subtitle = "Consultez vos paris en cours et terminés.")
    object FRIENDS : TopLevelDestination(route = Routes.FRIENDS, title = "AMIS", subtitle = "Défiez vos porches en les ajoutant en amis.")
    object CURRENT_BETS : TopLevelDestination(route = Routes.CURRENT_BETS, title = "BETS EN COURS", subtitle = "Gérez vos bets et récompensez les gagnants.")
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

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape,
                drawerContainerColor = AllInTheme.colors.allIn_Dark
            ) {
                Column(
                    Modifier
                        .padding(top = 39.dp, bottom = 26.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    ProfilePicture(
                        borderWidth = 1.dp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Pseudo",
                        fontSize = 20.sp,
                        color = AllInTheme.colors.allIn_LighterGrey,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.W700)
                }
                topLevelDestinations.forEach { item ->
                    DrawerCell(
                        title = item.title,
                        subtitle = item.subtitle,
                        emoji = null,
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
                            tint = AllInTheme.colors.allIn_DarkGrey,
                            contentDescription = null)
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.offset( x = with(LocalDensity.current) {
                (abs(drawerWidth) + drawerOffset.value).toDp()
            }),
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



