package fr.iut.alldev.allin.ui.core.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun AllInTopBarCoinCounter(
    amount:Int,
    backgroundColor: Color = AllInTheme.colors.white,
    textColor: Color = AllInTheme.colors.allIn_Dark,
    iconColor: Color = AllInTheme.colors.allIn_Blue,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.wrapContentSize(), shape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50)) {
        Row(
            modifier = Modifier
            .background(backgroundColor)
            .padding(horizontal = 13.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = amount.toString(),
                color = textColor,
                style = AllInTheme.typography.h1,
                fontSize = 20.sp)
            Icon(
                painter = AllInTheme.icons.allCoins(),
                tint = iconColor,
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun AllInTopBarCoinCounterPreview() {
    AllInTheme {
        AllInTopBarCoinCounter(547)
    }
}