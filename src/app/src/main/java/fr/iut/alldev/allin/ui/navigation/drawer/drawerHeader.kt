package fr.iut.alldev.allin.ui.navigation.drawer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.ui.core.ProfilePicture
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun DrawerHeader(
    nbBets: Int,
    bestWin: Int,
    nbFriends: Int,
    username: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfilePicture(
            borderWidth = 1.dp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = username,
            fontSize = 20.sp,
            color = AllInTheme.colors.allIn_LightGrey200,
            textAlign = TextAlign.Center,
            style = AllInTheme.typography.h2,
        )
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 69.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DrawerHeaderStat(label = "Bets", value = nbBets)
            DrawerHeaderStat(label = "Meilleur gain", value = bestWin)
            DrawerHeaderStat(label = "Amis", value = nbFriends)
        }
    }
}


@Preview
@Composable
private fun DrawerHeaderPreview() {
    AllInTheme{
        DrawerHeader(
            nbBets = 114,
            bestWin = 360,
            nbFriends = 5,
            username = "Pseudo"
        )
    }
}