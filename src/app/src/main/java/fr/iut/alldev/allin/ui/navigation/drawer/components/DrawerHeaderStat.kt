package fr.iut.alldev.allin.ui.navigation.drawer.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun DrawerHeaderStat(
    label: String,
    value: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value.toString(),
            color = AllInTheme.colors.white,
            style = AllInTheme.typography.h1
        )
        Text(
            text = label,
            color = AllInTheme.colors.allInLightGrey300,
            style = AllInTheme.typography.p1
        )
    }
}

@Preview
@Composable
private fun DrawerHeaderStatPreview() {
    AllInTheme {
        DrawerHeaderStat(label = "Amis", value = 5)
    }
}