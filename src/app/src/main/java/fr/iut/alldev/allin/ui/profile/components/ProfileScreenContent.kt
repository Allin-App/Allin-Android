package fr.iut.alldev.allin.ui.profile.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun ProfileScreenContent(
    username: String,
    image: String?,
    totalBets: Int,
    bestWin: Int,
    friends: Int,
    selectNewProfilePicture: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 18.dp, horizontal = 21.dp)
    ) {
        ProfileScreenHeader(
            image = image,
            username = username,
            totalBets = totalBets,
            bestWin = bestWin,
            friends = friends,
            selectNewProfilePicture = selectNewProfilePicture
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProfileScreenContentPreview() {
    AllInTheme {
        ProfileScreenContent(
            username = "User 1",
            image = null,
            totalBets = 12,
            bestWin = 365,
            friends = 5,
            selectNewProfilePicture = { }
        )
    }
}