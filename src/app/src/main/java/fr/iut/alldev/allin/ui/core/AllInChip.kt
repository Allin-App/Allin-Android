package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.theme.AllInTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllInChip(
    text: String,
    isSelected: Boolean = false,
    onClick: ()->Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        onClick = onClick,
        border = if(!isSelected) BorderStroke(1.dp, AllInTheme.themeColors.border) else null,
        colors = CardDefaults.cardColors(
            containerColor = with(AllInTheme){
                if(isSelected) colors.allIn_Purple else themeColors.background
            }
        )
    ) {
        Box{
            Text(
                text = text,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 22.dp)
                    .alpha(if(isSelected) 0f else 1f),
                textAlign = TextAlign.Center,
                style = AllInTheme.typography.r,
                color = AllInTheme.themeColors.on_background_2
            )
            if(isSelected) {
                Text(
                    text = text,
                    modifier = modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    style = AllInTheme.typography.h1,
                    color = AllInTheme.colors.white

                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInChipPreviewUnselected() {
    AllInTheme {
        AllInChip("Public", false)
    }
}

@Preview
@Composable
private fun AllInChipPreviewSelected() {
    AllInTheme {
        AllInChip("Public", true)
    }
}