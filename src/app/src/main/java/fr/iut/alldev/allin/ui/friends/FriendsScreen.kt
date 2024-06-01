package fr.iut.alldev.allin.ui.friends

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.iut.alldev.allin.data.model.FriendStatus
import fr.iut.alldev.allin.ui.friends.components.FriendsScreenContent

@Composable
fun FriendsScreen(
    viewModel: FriendsScreenViewModel = hiltViewModel()
) {
    val search by viewModel.search.collectAsStateWithLifecycle()
    val addTabState by viewModel.addTabState.collectAsStateWithLifecycle()
    val requestsTabState by viewModel.requestsTabState.collectAsStateWithLifecycle()
    val refreshing by viewModel.refreshing.collectAsStateWithLifecycle()

    val focus = LocalFocusManager.current

    FriendsScreenContent(
        addTabState = addTabState,
        search = search,
        setSearch = { viewModel.setSearch(it) },
        onToggleDeleteFriend = {
            if (it.friendStatus == FriendStatus.NOT_FRIEND) {
                viewModel.addFriend(it.username)
            } else {
                viewModel.removeFriend(it.username)
            }
        },
        requestsTabState = requestsTabState,
        acceptRequest = { viewModel.addFriend(it.username) },
        declineRequest = { viewModel.removeFriend(it.username) },
        refresh = {
            focus.clearFocus()
            viewModel.refreshAll()
        },
        isRefreshing = refreshing
    )
}