package fr.iut.alldev.allin.ui.core.bet

import androidx.compose.foundation.layout.Box
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
import fr.iut.alldev.allin.ui.core.ProfilePicture
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun BetProfilePictureRow(
    pictures: List<Painter?>,
    maxLength: Int = 5,
    modifier: Modifier = Modifier
) {
    val nRepeat = remember{
        if (pictures.size > maxLength) maxLength else pictures.size
    }

    Box(
        modifier.width((nRepeat*17).dp)
    ){
        pictures.forEachIndexed { index, painter ->
            ProfilePicture(
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
        BetProfilePictureRow(pictures = listOf(
            painterResource(id = R.drawable.money_with_wings),
            null,
            painterResource(id = R.drawable.money_with_wings)
        ))
    }
}
@Preview
@Composable
private fun BetProfilePictureRowMaxPreview() {
    AllInTheme {
        BetProfilePictureRow(pictures = listOf(
            painterResource(id = R.drawable.money_with_wings),
            null,
            painterResource(id = R.drawable.money_with_wings),
            null,
            painterResource(id = R.drawable.money_with_wings),
            null
        ))
    }
}
