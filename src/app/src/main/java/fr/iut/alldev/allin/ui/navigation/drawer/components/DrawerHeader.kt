package fr.iut.alldev.allin.ui.navigation.drawer.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ext.asFallbackProfileUsername
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.ProfilePicture

@Composable
fun DrawerHeader(
    nbBets: Int,
    bestWin: Int,
    nbFriends: Int,
    username: String,
    image: String?,
    modifier: Modifier = Modifier,
    navigateToProfile: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfilePicture(
            image = image,
            fallback = username.asFallbackProfileUsername(),
            borderWidth = 1.dp,
            modifier = Modifier.clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = navigateToProfile
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = username,
            fontSize = 20.sp,
            color = AllInColorToken.allInLightGrey200,
            textAlign = TextAlign.Center,
            style = AllInTheme.typography.h2,
        )
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 69.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DrawerHeaderStat(label = stringResource(id = R.string.drawer_bets), value = nbBets)
            DrawerHeaderStat(label = stringResource(id = R.string.drawer_best_win), value = bestWin)
            DrawerHeaderStat(label = stringResource(id = R.string.drawer_friends), value = nbFriends)
        }
    }
}


@Preview
@Composable
private fun DrawerHeaderPreview() {
    AllInTheme {
        DrawerHeader(
            nbBets = 114,
            bestWin = 360,
            nbFriends = 5,
            username = "Pseudo",
            image = null,
            navigateToProfile = { }
        )
    }
}