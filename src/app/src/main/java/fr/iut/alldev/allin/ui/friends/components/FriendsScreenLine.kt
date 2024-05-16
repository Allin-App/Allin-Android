package fr.iut.alldev.allin.ui.friends.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInButton
import fr.iut.alldev.allin.ui.core.ProfilePicture

@Composable
fun FriendsScreenLine(
    username: String,
    isFriend: Boolean,
    toggleIsFriend: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ProfilePicture(
            size = 50.dp
        )

        Text(
            text = username,
            color = AllInTheme.colors.onBackground2,
            style = AllInTheme.typography.sm2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 15.sp,
            modifier = Modifier.weight(1f)
        )

        AllInButton(
            color = if (isFriend) {
                AllInTheme.colors.background
            } else {
                AllInColorToken.allInPurple
            },
            text = if (isFriend) {
                stringResource(id = R.string.generic_delete)
            } else {
                stringResource(id = R.string.generic_add)
            },
            textColor = if (isFriend) {
                AllInTheme.colors.onBackground
            } else {
                AllInColorToken.white
            },
            isSmall = true,
            textStyle = AllInTheme.typography.sm2,
            onClick = toggleIsFriend,
            modifier = Modifier.weight(.5f)
        )
    }
}

@Preview
@Composable
private fun FriendsScreenLinePreview() {
    AllInTheme {
        FriendsScreenLine(
            username = "Random",
            isFriend = false,
            toggleIsFriend = { }
        )
    }
}

@Preview
@Composable
private fun FriendsScreenLineIsFriendPreview() {
    AllInTheme {
        FriendsScreenLine(
            username = "Random",
            isFriend = true,
            toggleIsFriend = { }
        )
    }
}