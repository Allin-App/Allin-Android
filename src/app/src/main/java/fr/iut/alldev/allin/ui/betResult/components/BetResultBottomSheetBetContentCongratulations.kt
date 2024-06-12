package fr.iut.alldev.allin.ui.betResult.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun BetResultBottomSheetContentCongratulations(
    modifier: Modifier = Modifier,
    username: String
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val rotation by infiniteTransition.animateFloat(
        initialValue = -11f,
        targetValue = -5f,
        animationSpec = infiniteRepeatable(
            animation = tween(900),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = .9f,
        animationSpec = infiniteRepeatable(
            animation = tween(900),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Row(
        modifier = modifier
            .rotate(rotation)
            .scale(scale),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.bet_result_congratulations),
            style = AllInTheme.typography.h1,
            fontSize = 25.sp,
            fontStyle = FontStyle.Italic,
            color = AllInColorToken.white
        )
        Text(
            text = "${username.uppercase()} !",
            style = AllInTheme.typography.h1,
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic,
            color = AllInColorToken.white
        )
    }
}
