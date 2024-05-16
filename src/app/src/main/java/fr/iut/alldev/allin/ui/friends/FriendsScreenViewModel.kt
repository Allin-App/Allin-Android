package fr.iut.alldev.allin.ui.friends

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsScreenViewModel @Inject constructor(
) : ViewModel() {

    val search by lazy { mutableStateOf("") }
    val state by lazy { mutableStateOf<State>(State.Loading) }

    init {
        viewModelScope.launch {
            state.value = State.Loaded(mockFriends)
        }
    }

    sealed class State {
        data object Loading: State()

        data class Loaded(val friends: List<User>): State()
    }
}

private val mockFriends by lazy {
    listOf(
        User(
            id = "1",
            username = "Owen",
            email = "",
            coins = 8533
        ),
        User(
            id = "2",
            username = "Dave",
            email = "",
            coins = 6942
        ),
        User(
            id = "3",
            username = "Lucas",
            email = "",
            coins = 3333
        ),
        User(
            id = "4",
            username = "Louison",
            email = "",
            coins = 1970
        ),
        User(
            id = "5",
            username = "Imri",
            email = "",
            coins = 1
        ),
        User(
            id = "12",
            username = "Owen",
            email = "",
            coins = 8533
        ),
        User(
            id = "22",
            username = "Dave",
            email = "",
            coins = 6942
        ),
        User(
            id = "32",
            username = "Lucas",
            email = "",
            coins = 3333
        ),
        User(
            id = "42",
            username = "Louison",
            email = "",
            coins = 1970
        ),
        User(
            id = "52",
            username = "Imri",
            email = "",
            coins = 1
        ),
        User(
            id = "13",
            username = "Owen",
            email = "",
            coins = 8533
        ),
        User(
            id = "23",
            username = "Dave",
            email = "",
            coins = 6942
        ),
        User(
            id = "33",
            username = "Lucas",
            email = "",
            coins = 3333
        ),
        User(
            id = "43",
            username = "Louison",
            email = "",
            coins = 1970
        ),
        User(
            id = "53",
            username = "Imri",
            email = "",
            coins = 1
        ),
        User(
            id = "14",
            username = "Owen",
            email = "",
            coins = 8533
        ),
        User(
            id = "24",
            username = "Dave",
            email = "",
            coins = 6942
        ),
        User(
            id = "34",
            username = "Lucas",
            email = "",
            coins = 3333
        ),
        User(
            id = "44",
            username = "Louison",
            email = "",
            coins = 1970
        ),
        User(
            id = "54",
            username = "Imri",
            email = "",
            coins = 1
        )
    )
}