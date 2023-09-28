package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.theme.AllInTheme

class SectionElement(
    val text: String,
    val content: @Composable ()->Unit
)

enum class DragAnchors(val fraction: Float) {
    Start(-2f),
    Center(0f),
    End(2f),
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllInSections(
    sections: List<SectionElement>,
    interSectionsPadding: Dp = 56.dp,
    modifier: Modifier = Modifier
) {
    var selected by remember {
        mutableStateOf(sections.firstOrNull())
    }
    var wasSwiped by remember{
        mutableStateOf(false)
    }

    val density = LocalDensity.current
    val draggableState = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Center,
            positionalThreshold = { distance: Float -> distance * .5f },
            velocityThreshold = { with(density) { 50.dp.toPx() } },
            animationSpec = spring(
                dampingRatio = 0.66f,
                stiffness = Spring.StiffnessMediumLow,
            ),
        ){
            val idx = sections.indexOf(selected)
            val add = when(it.fraction){
                DragAnchors.End.fraction-> {
                    -1
                }
                DragAnchors.Start.fraction-> {
                    1
                }
                else -> 0
            }
            selected=sections.getOrElse(idx+add){
                sections[idx]
            }
            if(selected!=sections[idx]){
                wasSwiped=true
            }
            false
        }
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

        Box(
            Modifier
                .wrapContentSize()
                .anchoredDraggable(draggableState, Orientation.Horizontal)
                .onSizeChanged { layoutSize ->
                    val dragEndPoint = layoutSize.width
                    draggableState.updateAnchors(
                        DraggableAnchors {
                            DragAnchors
                                .values()
                                .forEach { anchor ->
                                    anchor at dragEndPoint * anchor.fraction
                                }
                        }
                    )
                },
        ){
            Box(
                Modifier
                    .wrapContentSize()
                    .offset {
                        val offset =
                            if (
                                wasSwiped
                            ) {
                                if (!draggableState.isAnimationRunning) {
                                    wasSwiped = false
                                }
                                -draggableState.offset.toInt()

                            } else {
                                draggableState.offset.toInt()
                            }
                        IntOffset(x = offset, y = 0)
                    }
            ){
                selected?.content?.let { it() }
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