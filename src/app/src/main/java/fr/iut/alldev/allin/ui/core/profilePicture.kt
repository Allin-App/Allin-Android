package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
@Preview
fun ProfilePicture() {
    Card(
        modifier = Modifier.size(80.dp),
        shape = RoundedCornerShape(100),
        colors = CardDefaults.cardColors(containerColor = AllInTheme.colors.allIn_DarkerGrey),
        border = BorderStroke(1.dp, AllInTheme.colors.allIn_DarkGrey)
    ) {
        Box(Modifier.fillMaxSize()) {
            Icon(imageVector = Icons.Filled.Camera,
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center))
        }
    }
}
