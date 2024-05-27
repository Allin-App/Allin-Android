package fr.iut.alldev.allin.ui.friends.components

import android.content.res.Configuration
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
import fr.iut.alldev.allin.data.model.FriendStatus
import fr.iut.alldev.allin.ext.asFallbackProfileUsername
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInButton
import fr.iut.alldev.allin.ui.core.ProfilePicture

@Composable
fun FriendsScreenLine(
    username: String,
    status: FriendStatus,
    toggleIsFriend: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ProfilePicture(
            fallback = username.asFallbackProfileUsername(),
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
            color = when (status) {
                FriendStatus.FRIEND -> AllInTheme.colors.background
                FriendStatus.NOT_FRIEND -> AllInColorToken.allInPurple
                FriendStatus.REQUESTED -> AllInTheme.colors.border
            },
            text = when (status) {
                FriendStatus.FRIEND -> {
                    stringResource(id = R.string.generic_delete)
                }
                FriendStatus.NOT_FRIEND -> {
                    stringResource(id = R.string.generic_add)
                }
                FriendStatus.REQUESTED -> {
                    stringResource(id = R.string.friends_request_sent)
                }
            },
            textColor = when (status) {
                FriendStatus.FRIEND -> AllInTheme.colors.onBackground
                FriendStatus.NOT_FRIEND -> AllInColorToken.white
                FriendStatus.REQUESTED -> AllInTheme.colors.onBackground2
            },
            isSmall = true,
            textStyle = AllInTheme.typography.sm2,
            onClick = toggleIsFriend,
            modifier = Modifier.weight(.8f)
        )
    }
}

@Preview
@Composable
private fun FriendsScreenLinePreview() {
    AllInTheme {
        FriendsScreenLine(
            username = "Random",
            status = FriendStatus.NOT_FRIEND,
            toggleIsFriend = { }
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FriendsScreenLineRequestedPreview() {
    AllInTheme {
        FriendsScreenLine(
            username = "Random",
            status = FriendStatus.REQUESTED,
            toggleIsFriend = { }
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FriendsScreenLineIsFriendPreview() {
    AllInTheme {
        FriendsScreenLine(
            username = "Random",
            status = FriendStatus.FRIEND,
            toggleIsFriend = { }
        )
    }
}