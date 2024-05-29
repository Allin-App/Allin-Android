package fr.iut.alldev.allin.ui.friends.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.ext.asPaddingValues
import fr.iut.alldev.allin.ui.friends.components.FriendsScreenRequestLine

@Composable
fun FriendsScreenRequestsTab(
    requests: List<User>,
    acceptRequest: (User) -> Unit,
    declineRequest: (User) -> Unit
) {
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
}