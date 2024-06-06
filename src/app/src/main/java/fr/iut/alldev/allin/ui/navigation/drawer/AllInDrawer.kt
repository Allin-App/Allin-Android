package fr.iut.alldev.allin.ui.navigation.drawer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.navigation.TopLevelDestination
import fr.iut.alldev.allin.ui.navigation.drawer.components.DrawerCell
import fr.iut.alldev.allin.ui.navigation.drawer.components.DrawerHeader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AllInDrawer(
    drawerState: DrawerState,
    destinations: List<TopLevelDestination>,
    scope: CoroutineScope,
    username: String,
    nbBets: Int,
    bestWin: Int,
    nbFriends: Int,
    image: String?,
    navigateTo: (String) -> Unit,
    logout: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape,
                drawerContainerColor = AllInColorToken.allInDark
            ) {
                DrawerHeader(
                    nbBets = nbBets,
                    bestWin = bestWin,
                    nbFriends = nbFriends,
                    username = username,
                    image = image,
                    modifier = Modifier.padding(top = 39.dp, bottom = 26.dp)
                )
                destinations.forEach { item ->
                    DrawerCell(
                        title = stringResource(item.title).uppercase(),
                        subtitle = stringResource(item.subtitle),
                        emoji = painterResource(id = item.emoji),
                        onClick = {
                            scope.launch { drawerState.close() }
                            navigateTo(item.route)
                        },
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 13.dp)
                    )
                }
                TextButton(
                    onClick = logout,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(id = R.string.generic_logout),
                        style = AllInTheme.typography.sm1,
                        color = AllInColorToken.allInDarkGrey50,
                        fontSize = 16.sp
                    )
                }
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(start = 19.dp, bottom = 10.dp)
                ) {
                    IconButton(
                        onClick = { /*TODO : Settings*/ },
                        modifier = Modifier.align(Alignment.BottomStart)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            modifier = Modifier.size(40.dp),
                            tint = AllInColorToken.allInDarkGrey50,
                            contentDescription = null
                        )
                    }
                }
            }
        },
        content = content
    )
}