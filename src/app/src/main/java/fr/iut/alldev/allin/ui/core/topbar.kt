package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun AllInTopBar(
    onMenuClicked: ()->Unit,
    coinAmount: Int
) {
    Box(modifier = Modifier.height(86.dp).fillMaxWidth()
        .background(brush = AllInTheme.colors.allIn_gradient)) {
        IconButton(onClick = onMenuClicked,
            modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(Icons.Default.Menu,
                contentDescription = null,
                tint = Color.White)
        }
        Icon(Icons.Default.Star,
            contentDescription = null,
            tint = AllInTheme.colors.white,
            modifier = Modifier.size(50.dp)
                .align(Alignment.Center))
        CoinCounter(amount = coinAmount, modifier = Modifier.align(Alignment.CenterEnd))
    }
}

@Preview
@Composable
private fun AllInTopBarPreview(){
    AllInTopBar(onMenuClicked = { }, coinAmount = 541)
}
