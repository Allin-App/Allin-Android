package fr.iut.alldev.allin.ui.core.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
    onMenuClicked: () -> Unit,
    coinAmount: Int,
) {
    TopAppBar(
        modifier = Modifier.background(AllInTheme.colors.allInMainGradient),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        title = { },
        navigationIcon = {
            IconButton(
                onClick = onMenuClicked,
                modifier = Modifier
            ) {
                Icon(
                    painterResource(id = R.drawable.allin_menu),
                    modifier = Modifier.size(30.dp),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        actions = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
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
                        .offset(x = 4.dp)
                )
            }
        }
    )
}

@Preview
@Composable
private fun AllInTopBarPreview() {
    AllInTheme {
         AllInTopBar(onMenuClicked = { }, coinAmount = 541)
    }
}
