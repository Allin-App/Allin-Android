package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        colors = CardDefaults.cardColors(containerColor = with(AllInTheme){
            if(isSelected) colors.allIn_Purple else themeColors.background
        })
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 22.dp),
            textAlign = TextAlign.Center,
            style = with(AllInTheme.typography) {
                if (isSelected) h1 else r
            },
            color = with(AllInTheme){
                if(isSelected) colors.white else themeColors.on_background_2
            }

        )
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