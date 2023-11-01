package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun PercentagePositionnedElement(
    percentage: Float,
    offset: Dp = (-9).dp,
    content: @Composable ()->Unit
) {
    Box(
        Modifier.fillMaxWidth()
    ) {
        when (percentage) {
            0f -> {
                content()
            }
            1f -> {
                Box(
                    Modifier.align(Alignment.CenterEnd)
                ){
                    content()
                }
            }
            else -> {
                Row {
                    Spacer(modifier = Modifier.fillMaxWidth(percentage))
                    Box(Modifier.offset(x = offset)) {
                        content()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PercentagePositionnedElementPreview() {
    AllInTheme {
        PercentagePositionnedElement(percentage = .4f) {
            Text("MyElement")
        }
    }
}