package fr.iut.alldev.allin.ui.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.core.snackbar.AllInSnackbar
import fr.iut.alldev.allin.ui.core.topbar.AllInTopBar
import kotlin.math.abs

@Composable
fun AllInScaffold(
    onMenuClicked: () -> Unit,
    coinAmount: Int,
    drawerState: DrawerState,
    snackbarHostState: SnackbarHostState,
    content: @Composable (PaddingValues) -> Unit,
) {

    var drawerWidth by remember { mutableFloatStateOf(drawerState.currentOffset) }
    LaunchedEffect(drawerState.currentOffset.isNaN()) {
        drawerWidth = drawerState.currentOffset
    }

    val localDensity = LocalDensity.current
    val contentOffset by remember {
        derivedStateOf {
            if (drawerWidth.isNaN() || drawerWidth == 0f) 0.dp
            else with(localDensity) {
                (abs(drawerWidth) + drawerState.currentOffset).toDp()
            }
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        modifier = Modifier.offset(x = contentOffset),
        snackbarHost = {
            AllInSnackbar(snackbarState = snackbarHostState)
        },
        topBar = {
            AllInTopBar(
                onMenuClicked = onMenuClicked,
                coinAmount = coinAmount
            )
        },
        content = content
    )
}