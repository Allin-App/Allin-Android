package fr.iut.alldev.allin.ui.core

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.ui.theme.AllInTheme


@Composable
fun AllInSectionButton(
    text: String,
    isSelected: Boolean,
    onClick: (Int)->Unit
) {
    val style = if(isSelected){
        AllInTheme.typography.h3.copy(
            color = AllInTheme.themeColors.on_main_surface,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold
        )
    }else{
        AllInTheme.typography.h3.copy(
            color = AllInTheme.themeColors.on_background_2,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
    }
    ClickableText(
        text = AnnotatedString(text),
        style = style,
        onClick = onClick,
        modifier = Modifier.animateContentSize()
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInSectionButtonPreview() {
    AllInTheme {
        AllInSectionButton(
            text = "Test",
            isSelected = false,
            onClick = {}
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AllInSectionButtonSelectedPreview() {
    AllInTheme {
        AllInSectionButton(
            text = "Test",
            isSelected = true,
            onClick = {}
        )
    }
}