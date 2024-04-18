package fr.iut.alldev.allin.ui.splash.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.Icon

@Composable
fun SplashScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AllInTheme.colors.allInLoginGradient),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = AllInTheme.icons.logo(),
            contentDescription = null,
            tint = AllInTheme.colors.white,
            modifier = Modifier.fillMaxSize(.25f)
        )
    }
}

@Preview
@Composable
private fun SplashScreenContentPreview() {
    AllInTheme {
        SplashScreenContent()
    }
}