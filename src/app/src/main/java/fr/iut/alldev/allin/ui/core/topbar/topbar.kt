package fr.iut.alldev.allin.ui.core.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun AllInTopBar(
    onMenuClicked: ()->Unit,
    coinAmount: Int
) {
    Box(
        modifier = Modifier
            .height(86.dp)
            .fillMaxWidth()
            .background(brush = AllInTheme.colors.allIn_MainGradient)
    ) {
        IconButton(
            onClick = onMenuClicked,
            modifier = Modifier
                .padding(start = 19.dp)
                .align(Alignment.CenterStart)
        ) {
            Icon(
                painterResource(id = R.drawable.allin_menu),
                modifier = Modifier.size(30.dp),
                contentDescription = null,
                tint = Color.White)
        }
        Icon(
            painter = painterResource(R.drawable.allin),
            contentDescription = null,
            tint = AllInTheme.colors.white,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center)
        )
        AllInTopBarCoinCounter(
            amount = coinAmount,
            modifier = Modifier
                .align(Alignment.CenterEnd)
        )
    }
}

@Preview
@Composable
private fun AllInTopBarPreview(){
    AllInTheme {
        AllInTopBar(onMenuClicked = { }, coinAmount = 541)
    }
}
