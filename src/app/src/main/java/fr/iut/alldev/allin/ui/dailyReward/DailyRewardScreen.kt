package fr.iut.alldev.allin.ui.dailyReward

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun DailyRewardScreen(
    amount: Int,
    onDismiss: () -> Unit
) {
    var hasOpened by remember { mutableStateOf(false) }
    DailyRewardScreenContent(
        amount = amount,
        hasOpened = hasOpened
    ) {
        if (hasOpened) {
            onDismiss()
        } else {
            hasOpened = true
        }
    }

}

@Composable
fun DailyRewardScreenContent(
    amount: Int,
    hasOpened: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 3f,
        targetValue = -3f,
        animationSpec = infiniteRepeatable(
            animation = tween(900),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = .95f,
        animationSpec = infiniteRepeatable(
            animation = tween(900),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    0f to AllInColorToken.black.copy(alpha = .71f),
                    1f to AllInColorToken.black.copy(alpha = .97f)
                )
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.daily_reward_title),
            style = AllInTheme.typography.h1,
            fontSize = 20.sp,
            color = AllInColorToken.white,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 48.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(horizontal = 48.dp),
            contentAlignment = Alignment.Center
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

            androidx.compose.animation.AnimatedVisibility(
                visible = !hasOpened,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut(targetScale = 2f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.daily_reward_1),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .rotate(rotation)
                        .scale(scale)
                )
            }

            androidx.compose.animation.AnimatedVisibility(
                visible = hasOpened,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut(targetScale = 2f)
            ) {
                Box(
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.daily_reward_2),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .rotate(rotation)
                            .scale(scale)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .rotate(-rotation)
                            .scale(scale)
                    ) {
                        Text(
                            text = "+$amount",
                            style = AllInTheme.typography.h1,
                            fontSize = 70.sp,
                            color = AllInColorToken.white
                        )
                        Icon(
                            painter = AllInTheme.icons.allCoins(),
                            tint = AllInColorToken.white,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
        }
        Text(
            text = stringResource(id = R.string.daily_reward_subtitle),
            style = AllInTheme.typography.l1,
            color = AllInColorToken.white,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 48.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DailyRewardScreenPreview() {
    AllInTheme {
        DailyRewardScreenContent(
            amount = 125,
            hasOpened = false,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DailyRewardScreenStep2Preview() {
    AllInTheme {
        DailyRewardScreenContent(
            amount = 125,
            hasOpened = true,
            onClick = {}
        )
    }
}