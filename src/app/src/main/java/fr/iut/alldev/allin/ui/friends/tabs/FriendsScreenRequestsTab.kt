package fr.iut.alldev.allin.ui.friends.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.ext.asPaddingValues
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.bet.AllInEmptyView
import fr.iut.alldev.allin.ui.friends.components.FriendsScreenRequestLine

@Composable
fun FriendsScreenRequestsTab(
    requests: List<User>,
    acceptRequest: (User) -> Unit,
    declineRequest: (User) -> Unit
) {
    Box {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = WindowInsets.navigationBars.asPaddingValues(start = 24.dp, end = 24.dp, top = 18.dp),
            verticalArrangement = Arrangement.spacedBy(11.dp),
        ) {
            items(requests) {
                FriendsScreenRequestLine(
                    username = it.username,
                    accept = { acceptRequest(it) },
                    decline = { declineRequest(it) }
                )
            }
        }
        if (requests.isEmpty()) {
            AllInEmptyView(
                text = stringResource(id = R.string.friends_requests_empty_text),
                subtext = null,
                image = painterResource(id = R.drawable.mailbox),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun FriendsScreenRequestsTabPreview() {
    AllInTheme {
        FriendsScreenRequestsTab(
            requests = emptyList(),
            acceptRequest = {  },
            declineRequest = {  }

        )
    }
}