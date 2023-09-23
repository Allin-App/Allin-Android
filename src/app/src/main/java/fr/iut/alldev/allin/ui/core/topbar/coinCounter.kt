package fr.iut.alldev.allin.ui.core.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun CoinCounter(amount:Int, modifier: Modifier = Modifier) {
    Card(modifier = modifier.wrapContentSize(), shape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50)) {
        Row(modifier = Modifier
            .background(AllInTheme.colors.white)
            .padding(horizontal = 13.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.allcoin),
                tint = AllInTheme.colors.allIn_Blue,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp))
            Text(text = amount.toString(),
                color = AllInTheme.colors.allIn_Dark,
                style = AllInTheme.typography.h1,
                fontSize = 20.sp)
        }
    }
}

@Preview
@Composable
private fun CoinCounterPreview() {
    AllInTheme {
        CoinCounter(547)
    }
}