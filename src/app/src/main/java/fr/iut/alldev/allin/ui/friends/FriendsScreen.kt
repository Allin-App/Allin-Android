package fr.iut.alldev.allin.ui.friends

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.ui.core.AllInLoading
import fr.iut.alldev.allin.ui.friends.components.FriendsScreenContent

@Composable
fun FriendsScreen(
    viewModel: FriendsScreenViewModel = hiltViewModel()
) {
    var search by remember { viewModel.search }
    val state by remember { viewModel.state }

    when (val s = state) {
        is FriendsScreenViewModel.State.Loaded -> {
            var deleted by remember { mutableStateOf(emptyList<User>()) }
            val filteredFriends = remember(search) {
                s.friends.filter {
                    it.username.contains(search, ignoreCase = true)
                }
            }

            FriendsScreenContent(
                friends = filteredFriends,
                deleted = deleted,
                search = search,
                setSearch = { search = it },
                onToggleDeleteFriend = {
                    deleted = if (deleted.contains(it)) {
                        deleted - it
                    } else {
                        deleted + it
                    }
                }
            )
        }

        FriendsScreenViewModel.State.Loading -> {
            AllInLoading(visible = true)
        }
    }
}