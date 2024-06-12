package fr.iut.alldev.allin.ui.betCreation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@Composable
fun BetCreationScreenCustomAnswer(
    text: String,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = AbsoluteSmoothCornerShape(15.dp, 100),
        color = AllInColorToken.allInPurple,
        contentColor = AllInColorToken.white
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .padding(start = 8.dp, end = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = AllInTheme.typography.h1
            )
            IconButton(
                onClick = onDelete,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}


@Preview
@Composable
private fun BetCreationScreenCustomAnswerPreview() {
    AllInTheme {
        BetCreationScreenCustomAnswer(
            text = "Text",
            onDelete = {}
        )
    }

}