package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.theme.AllInTheme
import kotlinx.coroutines.launch

class SectionElement(
    val text: String,
    val content: @Composable () -> Unit
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllInSections(
    sections: List<SectionElement>,
    modifier: Modifier = Modifier,
    interSectionsPadding: Dp = 56.dp,
    onLoadSection: ()->Unit = { }
) {
    val pagerState = rememberPagerState(pageCount = { sections.size })
    val scope = rememberCoroutineScope()
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        stickyHeader {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            0.75f to AllInTheme.themeColors.mainSurface,
                            1f to Color.Transparent
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(interSectionsPadding),
                    modifier = Modifier.padding(vertical = 12.dp)
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
            }
        }

        item {
            HorizontalPager(
                state = pagerState
            ) { page ->
                LaunchedEffect(key1 = page) { onLoadSection() }
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    sections[page].content()
                }
            }
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