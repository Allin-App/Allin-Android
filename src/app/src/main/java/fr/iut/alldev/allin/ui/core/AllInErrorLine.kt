package fr.iut.alldev.allin.ui.core

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun AllInErrorLine(text: String) {
    Text(
        text = text,
        style = AllInTheme.typography.r,
        color = Color.Red,
        fontSize = 10.sp,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview
@Composable
private fun AllInErrorLinePreview() {
    AllInTheme{
        AllInErrorLine("Lorem Ipsum.")
    }
}