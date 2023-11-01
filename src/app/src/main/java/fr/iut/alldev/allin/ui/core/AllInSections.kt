package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.theme.AllInTheme
import kotlinx.coroutines.launch

class SectionElement(
    val text: String,
    val content: @Composable ()->Unit
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllInSections(
    sections: List<SectionElement>,
    interSectionsPadding: Dp = 56.dp,
    modifier: Modifier = Modifier,
    onLoadSection: ()->Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = {
        sections.size
    })
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LazyRow(
                horizontalArrangement = Arrangement.spacedBy(interSectionsPadding)
            ) {
                itemsIndexed(sections) { index, section ->
                    AllInSectionButton(
                        text = section.text,
                        isSelected = index == pagerState.currentPage,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }


        HorizontalPager(state = pagerState) { page ->
            LaunchedEffect(key1 = page){
                onLoadSection()
            }
            sections[page].content()
        }
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