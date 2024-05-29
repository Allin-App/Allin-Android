package fr.iut.alldev.allin.ui.friends.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.data.model.FriendStatus
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.ext.asPaddingValues
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInTextField
import fr.iut.alldev.allin.ui.friends.components.FriendsScreenLine

@Composable
fun FriendsScreenAddTab(
    friends: List<User>,
    search: String,
    onToggleDeleteFriend: (User) -> Unit,
    setSearch: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = WindowInsets.navigationBars.asPaddingValues(start = 24.dp, end = 24.dp, top = 18.dp),
        verticalArrangement = Arrangement.spacedBy(11.dp),
    ) {
        stickyHeader {
            AllInTextField(
                value = search,
                onValueChange = setSearch,
                leadingIcon = rememberVectorPainter(image = Icons.Default.Search),
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            0.5f to AllInTheme.colors.mainSurface,
                            1f to Color.Transparent
                        )
                    )
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        items(friends) {
            FriendsScreenLine(
                username = it.username,
                status = it.friendStatus ?: FriendStatus.NOT_FRIEND,
                toggleIsFriend = { onToggleDeleteFriend(it) }
            )
        }

    }
}