package fr.iut.alldev.allin.ui.navigation.drawer.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.theme.AllInTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerCell(
    title: String,
    subtitle: String,
    emoji: Painter,
    onClick: ()->Unit,
    modifier: Modifier = Modifier
) {
    Card(onClick = onClick,
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = AllInTheme.colors.allIn_DarkGrey50),
        shape = RoundedCornerShape(20),
        colors = CardDefaults.cardColors(containerColor = AllInTheme.colors.allIn_DarkGrey100)) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 17.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = emoji,
                contentDescription = null,
                modifier = Modifier
                    .height(28.dp)
                    .padding(end = 11.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = AllInTheme.typography.h2,
                    fontSize = 13.sp,
                    color = AllInTheme.colors.white,
                    fontWeight = FontWeight.W700
                )
                Text(
                    text = subtitle,
                    style = AllInTheme.typography.r,
                    fontSize = 9.sp,
                    color = AllInTheme.colors.allIn_LightGrey300,
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                tint = AllInTheme.colors.allIn_DarkGrey50,
                contentDescription = null
            )

        }
    }
}

@Preview
@Composable
private fun DrawerCellPreview() {
    AllInTheme {
        DrawerCell(
            title = "CREER UN BET",
            subtitle = "Créez un nouveau BET",
            emoji = painterResource(id = R.drawable.video_game),
            onClick = {}
        )
    }
}