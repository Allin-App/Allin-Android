package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.theme.AllInTheme

class SectionElement(
    val text: String,
    val content: @Composable ()->Unit
)

@Composable
fun AllInSections(
    sections: List<SectionElement>,
    interSectionsPadding: Dp = 56.dp,
    modifier: Modifier = Modifier
) {
    var selected by remember{
        mutableStateOf(sections.firstOrNull())
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(interSectionsPadding)
        ) {
            items(sections) { section ->
                AllInSectionButton(
                    text = section.text,
                    isSelected = selected == section,
                    onClick = { selected = section }
                )
            }
        }
        selected?.content?.let { it() }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInSectionsPreview() {
    AllInTheme {
        AllInSections(
            sections = listOf(
                SectionElement("Page 1") {
                    Text("This is page 1")
                },
                SectionElement("Page 2") {
                    Text("This is page 2")
                },
            )
        )
    }
}