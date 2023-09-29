package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun AllInCoinCount(
    modifier: Modifier = Modifier,
    amount: Int,
    color: Color
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(
            text = amount.toString(),
            fontWeight = FontWeight.ExtraBold,
            color = color,
            style = AllInTheme.typography.h1,
            fontSize = 16.sp
        )
        Icon(
            painter = painterResource(id = R.drawable.allcoin),
            contentDescription = null,
            modifier = Modifier
                .size(13.dp),
            tint = color
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInCoinCountPreview() {
    AllInTheme {
        AllInCoinCount(amount = 542, color = AllInTheme.colors.allIn_Purple)
    }
}