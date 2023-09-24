package fr.iut.alldev.allin.ui.core

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import fr.iut.alldev.allin.ui.theme.AllInTheme


@Composable
fun HighlightedText(
    text: String,
    query: String,
    highlightStyle: SpanStyle,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        buildAnnotatedString {
            val startIndex = text.indexOf(query)
            val endIndex = startIndex + query.length
            if (startIndex != -1) {
                append(text.substring(0, startIndex))
                withStyle(highlightStyle) {
                    append(text.substring(startIndex, endIndex))
                }
                append(text.substring(endIndex))
            } else {
                append(text)
            }
        },
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        modifier = modifier,
        fontWeight = fontWeight,
        textAlign = textAlign,
        style = style
    )
}


@Preview
@Composable
private fun HighlightedTextPreview() {
    AllInTheme {
        HighlightedText(
            text = "Hello World !",
            query = "World",
            highlightStyle = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Red
            ),
            color = Color.White
        )
    }
}