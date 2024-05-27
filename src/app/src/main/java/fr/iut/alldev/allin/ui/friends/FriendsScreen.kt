package fr.iut.alldev.allin.ui.friends

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.iut.alldev.allin.data.model.FriendStatus
import fr.iut.alldev.allin.ui.core.AllInLoading
import fr.iut.alldev.allin.ui.friends.components.FriendsScreenContent

@Composable
fun FriendsScreen(
    viewModel: FriendsScreenViewModel = hiltViewModel()
) {
    val search by viewModel.search.collectAsStateWithLifecycle()
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val s = state) {
        is FriendsScreenViewModel.State.Loaded -> {
            var deleted by remember { mutableStateOf(emptyList<String>()) }
            var requested by remember { mutableStateOf(emptyList<String>()) }
            val filteredFriends = remember(search) {
                s.friends.filter {
                    it.username.contains(search, ignoreCase = true)
                }
            }

            FriendsScreenContent(
                friends = filteredFriends,
                deletedUsers = deleted,
                requestedUsers = requested,
                search = search,
                setSearch = { viewModel.setSearch(it) },
                onToggleDeleteFriend = {
                    deleted = if (deleted.contains(it.id) || it.friendStatus == FriendStatus.NOT_FRIEND) {
                        viewModel.addFriend(it.username)
                        requested = requested + it.id
                        deleted - it.id
                    } else {
                        viewModel.removeFriend(it.username)
                        if (requested.contains(it.id)) {
                            requested = requested - it.id
                        }
                        deleted + it.id
                    }
                }
            )
        }

        FriendsScreenViewModel.State.Loading -> {
            AllInLoading(visible = true)
        }
    }
}