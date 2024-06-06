package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun ProfilePicture(
    fallback: String,
    image: String?,
    modifier: Modifier = Modifier,
    borderWidth: Dp? = null,
    size: Dp = 80.dp
) {
    val shape = RoundedCornerShape(100)
    var hasImageloaded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.size(size),
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = AllInColorToken.allInDarkGrey100),
        border = borderWidth?.let { BorderStroke(it, AllInColorToken.allInDarkGrey50) }
    ) {
        Box(Modifier.fillMaxSize()) {
            image?.let {
                AsyncImage(
                    modifier = Modifier
                        .width(300.dp)
                        .height(174.dp)
                        .clip(shape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    onSuccess = { hasImageloaded = true },
                    contentScale = ContentScale.Crop
                )

                if (!hasImageloaded) {
                    Text(
                        text = fallback,
                        style = AllInTheme.typography.p2,
                        textAlign = TextAlign.Center,
                        fontSize = with(LocalDensity.current) { (size / 2).toSp() },
                        color = AllInColorToken.white,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            } ?: run {
                Text(
                    text = fallback,
                    style = AllInTheme.typography.p2,
                    textAlign = TextAlign.Center,
                    fontSize = with(LocalDensity.current) { (size / 2).toSp() },
                    color = AllInColorToken.white,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}


@Preview
@Composable
private fun ProfilePictureDefaultPreview() {
    AllInTheme {
        ProfilePicture(image = null, fallback = "LS")
    }
}