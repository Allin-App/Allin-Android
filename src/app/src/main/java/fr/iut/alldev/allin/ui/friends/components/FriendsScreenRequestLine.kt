package fr.iut.alldev.allin.ui.friends.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import fr.iut.alldev.allin.ext.asFallbackProfileUsername
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInButton
import fr.iut.alldev.allin.ui.core.ProfilePicture

@Composable
fun FriendsScreenRequestLine(
    username: String,
    accept: () -> Unit,
    decline: () -> Unit,
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
            color = AllInTheme.colors.onMainSurface,
            style = AllInTheme.typography.sm2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 15.sp,
            modifier = Modifier.weight(1f)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AllInButton(
                color = AllInColorToken.allInPurple,
                text = stringResource(id = R.string.friends_requests_accept),
                textColor = AllInColorToken.white,
                isSmall = true,
                textStyle = AllInTheme.typography.sm2,
                onClick = accept
            )

            IconButton(onClick = decline) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.friends_requests_decline),
                    tint = AllInTheme.colors.onBackground2
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FriendsScreenLinePreview() {
    AllInTheme {
        FriendsScreenRequestLine(
            username = "Xx_Bg_du_63",
            accept = { },
            decline = { }
        )
    }
}
