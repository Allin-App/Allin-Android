package fr.iut.alldev.allin.ui.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.core.topbar.AllInTopBar
import kotlin.math.abs

@Composable
fun AllInScaffold(
    onMenuClicked: ()->Unit,
    coinAmount: Int,
    drawerState: DrawerState,
    content: @Composable (PaddingValues)->Unit
) {

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

    Scaffold(
        modifier = Modifier.offset(x = contentOffset),
        topBar = {
            AllInTopBar(
                onMenuClicked = onMenuClicked,
                coinAmount = coinAmount
            )
        },
        content = content
    )
}