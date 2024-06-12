package fr.iut.alldev.allin.ui.profile.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ext.asFallbackProfileUsername
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.ProfilePicture

@Composable
fun ProfileScreenHeader(
    username: String,
    image: String?,
    totalBets: Int,
    bestWin: Int,
    friends: Int,
    selectNewProfilePicture: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ProfilePicture(
            image = image,
            fallback = username.asFallbackProfileUsername(),
            size = 68.dp,
            modifier = Modifier.clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = selectNewProfilePicture
            )
        )
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.height(68.dp)
        ) {
            Text(
                text = username,
                style = AllInTheme.typography.h2,
                color = AllInTheme.colors.onMainSurface,
                fontSize = 20.sp
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = totalBets.toString(),
                        style = AllInTheme.typography.h1,
                        color = AllInTheme.colors.onMainSurface,
                        fontSize = 14.sp,
                    )
                    Text(
                        text = stringResource(id = R.string.drawer_bets),
                        style = AllInTheme.typography.p1,
                        color = AllInTheme.colors.onBackground2
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = bestWin.toString(),
                        style = AllInTheme.typography.h1,
                        color = AllInTheme.colors.onMainSurface,
                        fontSize = 14.sp
                    )
                    Text(
                        text = stringResource(id = R.string.drawer_best_win),
                        style = AllInTheme.typography.p1,
                        color = AllInTheme.colors.onBackground2
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = friends.toString(),
                        style = AllInTheme.typography.h1,
                        color = AllInTheme.colors.onMainSurface,
                        fontSize = 14.sp
                    )
                    Text(
                        text = stringResource(id = R.string.drawer_friends),
                        style = AllInTheme.typography.p1,
                        color = AllInTheme.colors.onBackground2
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProfileScreenHeaderPreview() {
    AllInTheme {
        ProfileScreenHeader(
            username = "User 1",
            image = null,
            totalBets = 12,
            bestWin = 365,
            friends = 5,
            selectNewProfilePicture = { }
        )
    }
}