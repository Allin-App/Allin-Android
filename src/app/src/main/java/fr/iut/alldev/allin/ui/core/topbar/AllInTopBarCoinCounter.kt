package fr.iut.alldev.allin.ui.core.topbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun AllInTopBarCoinCounter(
    amount: Int,
    modifier: Modifier = Modifier,
    backgroundColor: Color = AllInColorToken.white,
    textColor: Color = AllInColorToken.allInDark,
    iconColor: Color = AllInColorToken.allInBlue,
) {
    var oldAmount by remember { mutableIntStateOf(amount) }
    LaunchedEffect(amount) {
        oldAmount = amount
    }

    val countString = remember(amount) { amount.toString() }
    val oldCountString = remember(oldAmount) { oldAmount.toString() }

    Card(
        modifier = modifier.wrapContentSize(),
        shape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50)
    ) {
        Row(
            modifier = Modifier
                .background(backgroundColor)
                .padding(horizontal = 13.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                for (i in countString.indices) {
                    val oldChar = oldCountString.getOrNull(i)
                    val newChar = countString[i]
                    val char = if (oldChar == newChar) {
                        oldCountString[i]
                    } else {
                        countString[i]
                    }

                    AnimatedContent(
                        targetState = char,
                        transitionSpec = {
                            val delayMillis = (countString.indices.count() - i) * 50
                            if (oldAmount <= amount) {
                                (slideInVertically(tween(delayMillis)) { it } togetherWith
                                        slideOutVertically(tween(delayMillis)) { -it })
                            } else {
                                (slideInVertically(tween(delayMillis)) { -it } togetherWith
                                        slideOutVertically(tween(delayMillis)) { it })
                            }
                        },
                        label = ""
                    ) { char ->
                        Text(
                            text = char.toString(),
                            style = AllInTheme.typography.h1,
                            color = textColor,
                            fontSize = 20.sp,
                            softWrap = false,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                    }
                }
            }
            Icon(
                painter = AllInTheme.icons.allCoins(),
                tint = iconColor,
                contentDescription = null,
                modifier = Modifier.padding(vertical = 5.dp)
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