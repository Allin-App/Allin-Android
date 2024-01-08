package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun ProfilePicture(
    modifier: Modifier = Modifier,
    image: Painter? = null,
    borderWidth: Dp? = null,
    size: Dp = 80.dp,
) {
    val shape = RoundedCornerShape(100)
    Card(
        modifier = modifier.size(size),
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = AllInTheme.colors.allInDarkGrey100),
        border = borderWidth?.let { BorderStroke(it, AllInTheme.colors.allInDarkGrey50) }
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
                Icon(
                    imageVector = Icons.Default.PhotoCamera,
                    tint = AllInTheme.colors.white,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize(0.5f)
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
        ProfilePicture()
    }
}

@Preview
@Composable
private fun ProfilePicturePreview() {
    AllInTheme {
        ProfilePicture(
            image = painterResource(id = R.drawable.money_with_wings)
        )
    }
}