package fr.iut.alldev.allin.ui.betResult.components

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInCoinCount
import fr.iut.alldev.allin.ui.core.IconPosition

@Composable
fun BetResultBottomSheetContentCoinAmount(
    modifier: Modifier = Modifier,
    amount: Int
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.bet_result_you_win),
            style = AllInTheme.typography.sm2,
            color = AllInTheme.colors.white,
            fontSize = 20.sp
        )

        Box(
            modifier
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(100.dp),
                    color = AllInTheme.colors.white
                )
                .padding(vertical = 22.dp, horizontal = 33.dp)

        ) {
            AllInCoinCount(
                amount = amount,
                textStyle = AllInTheme.typography.h1,
                position = IconPosition.TRAILING,
                color = AllInTheme.colors.white,
                size = 60,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-6).dp)
            )
            AllInCoinCount(
                amount = amount,
                textStyle = AllInTheme.typography.h1,
                position = IconPosition.TRAILING,
                color = AllInTheme.colors.white.copy(alpha = .32f),
                size = 60,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetResultBottomSheetContentCoinAmountPreview() {
    AllInTheme {
        BetResultBottomSheetContentCoinAmount(amount = 1572)
    }
}
