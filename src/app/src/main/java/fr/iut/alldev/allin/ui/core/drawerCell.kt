package fr.iut.alldev.allin.ui.core

import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.ui.theme.AllInTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerCell(
    title: String,
    subtitle: String,
    emoji: Image?,
    onClick: ()->Unit,
    modifier: Modifier = Modifier
) {
    Card(onClick = onClick,
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = AllInTheme.colors.allIn_DarkGrey),
        shape = RoundedCornerShape(20),
        colors = CardDefaults.cardColors(containerColor = AllInTheme.colors.allIn_DarkerGrey)) {
        Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 17.dp)) {
            Icon(imageVector = Icons.Default.Face,
                contentDescription = null,
                modifier = Modifier.padding(end = 11.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 13.sp,
                    color = AllInTheme.colors.white,
                    fontWeight = FontWeight.W700
                )
                Text(text = subtitle,
                    fontSize = 9.sp,
                    color = AllInTheme.colors.allIn_LightGrey)
            }
            Icon(imageVector = Icons.Default.ChevronRight,
                tint = AllInTheme.colors.allIn_DarkGrey,
                contentDescription = null)

        }
    }
}

@Preview
@Composable
private fun DrawerCellPreview() {
    DrawerCell(
        title = "CREER UN BET",
        subtitle = "Créez un nouveau BET",
        emoji = null,
        onClick = {}
    )
}