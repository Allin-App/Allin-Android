package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun ProfilePicture(
    fallback: String,
    modifier: Modifier = Modifier,
    image: Painter? = null,
    borderWidth: Dp? = null,
    size: Dp = 80.dp,
) {
    val shape = RoundedCornerShape(100)
    Card(
        modifier = modifier.size(size),
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = AllInColorToken.allInDarkGrey100),
        border = borderWidth?.let { BorderStroke(it, AllInColorToken.allInDarkGrey50) }
    ) {
        Box(Modifier.fillMaxSize()) {
            image?.let {
                Image(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                        .clip(shape)
                )
            } ?: run {
                Text(
                    text = fallback,
                    style = AllInTheme.typography.p2,
                    textAlign = TextAlign.Center,
                    fontSize = with(LocalDensity.current) { (size / 2).toSp() },
                    color = AllInColorToken.white,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(shape)
                )
            }
        }
    }
}


@Preview
@Composable
private fun ProfilePictureDefaultPreview() {
    AllInTheme {
        ProfilePicture("LS")
    }
}

@Preview
@Composable
private fun ProfilePicturePreview() {
    AllInTheme {
        ProfilePicture(
            fallback = "LS",
            image = painterResource(id = R.drawable.money_with_wings)
        )
    }
}