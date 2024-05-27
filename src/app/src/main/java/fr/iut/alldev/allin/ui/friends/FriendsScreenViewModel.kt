package fr.iut.alldev.allin.ui.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.data.repository.FriendRepository
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FriendsScreenViewModel @Inject constructor(
    private val friendRepository: FriendRepository,
    private val keystoreManager: AllInKeystoreManager
) : ViewModel() {

    private val _search by lazy { MutableStateFlow("") }
    val search get() = _search.asStateFlow()

    private val _state by lazy { MutableStateFlow<State>(State.Loading) }
    val state get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _state.emit(
                    State.Loaded(
                        friends = friendRepository.getFriends(
                            token = keystoreManager.getTokenOrEmpty()
                        )
                    )
                )
            } catch (e: Exception) {
                Timber.e(e)
            }

            _search.collect { itSearch ->
                try {
                    _state.emit(
                        if (itSearch.isNotBlank()) {
                            State.Loaded(
                                friends = friendRepository.searchNew(
                                    token = keystoreManager.getTokenOrEmpty(),
                                    search = itSearch
                                )
                            )
                        } else {
                            State.Loaded(
                                friends = friendRepository.getFriends(
                                    token = keystoreManager.getTokenOrEmpty()
                                )
                            )
                        }
                    )
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }
    }

    fun setSearch(search: String) {
        viewModelScope.launch {
            _search.emit(search)
        }
    }

    fun addFriend(username: String) {
        viewModelScope.launch {
            try {
                friendRepository.add(
                    token = keystoreManager.getTokenOrEmpty(),
                    username = username
                )
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    fun removeFriend(username: String) {
        viewModelScope.launch {
            try {
                friendRepository.remove(
                    token = keystoreManager.getTokenOrEmpty(),
                    username = username
                )
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    sealed interface State {
        data object Loading : State
        data class Loaded(val friends: List<User>) : State
    }
}

