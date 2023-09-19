package fr.iut.alldev.allin.ui.core.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun CoinCounter(amount:Int, modifier: Modifier = Modifier) {
    Card(modifier = modifier.wrapContentSize(), shape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50)) {
        Row(modifier = Modifier.background(AllInTheme.colors.white)
            .padding(horizontal = 13.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.ShoppingCart,
                tint = AllInTheme.colors.allIn_Dark,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp))
            Text(text = amount.toString(),
                color = AllInTheme.colors.allIn_Dark,
                fontWeight = FontWeight.W800,
                fontSize = 20.sp)
        }
    }
}

@Preview
@Composable
private fun CoinCounterPreview() {
    CoinCounter(547)
}