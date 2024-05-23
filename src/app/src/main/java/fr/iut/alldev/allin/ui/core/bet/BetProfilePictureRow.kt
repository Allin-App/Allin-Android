package fr.iut.alldev.allin.ui.core.bet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ext.asFallbackProfileUsername
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.ProfilePicture

@Composable
fun BetProfilePictureRow(
    pictures: List<Pair<String, Painter?>>,
    modifier: Modifier = Modifier,
    maxLength: Int = 5
) {
    val nRepeat = remember{ pictures.size.coerceAtMost(maxLength) }

    Box(modifier.width((35 + (nRepeat - 1) * 17).dp)){
        pictures.take(nRepeat).forEachIndexed { index, (username, painter) ->
            ProfilePicture(
                fallback = username.asFallbackProfileUsername(),
                image = painter,
                size = 35.dp,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = (index * -17).dp)
                    .zIndex(-index.toFloat())
            )
        }
    }
}

@Preview
@Composable
private fun BetProfilePictureRowPreview() {
    AllInTheme {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            BetProfilePictureRow(
                pictures = listOf("lucas" to null)
            )
        }
    }
}
@Preview
@Composable
private fun BetProfilePictureRowMaxPreview() {
    AllInTheme {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            BetProfilePictureRow(
                pictures = listOf(
                    "lucas" to painterResource(id = R.drawable.money_with_wings),
                    "lucas" to null,
                    "lucas" to painterResource(id = R.drawable.money_with_wings),
                    "lucas" to null,
                    "lucas" to painterResource(id = R.drawable.money_with_wings),
                    "lucas" to null
                )
            )
        }
    }
}
