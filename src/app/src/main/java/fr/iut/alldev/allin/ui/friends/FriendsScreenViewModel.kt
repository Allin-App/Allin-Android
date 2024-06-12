package fr.iut.alldev.allin.ui.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.FriendStatus
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.data.repository.FriendRepository
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class FriendsScreenViewModel @Inject constructor(
    private val friendRepository: FriendRepository,
    private val keystoreManager: AllInKeystoreManager
) : ViewModel() {

    private val _search by lazy { MutableStateFlow("") }
    val search get() = _search.asStateFlow()

    private val _addTabState by lazy { MutableStateFlow<AddTabState>(AddTabState.Loading) }
    val addTabState get() = _addTabState.asStateFlow()

    private val _requestsTabState by lazy { MutableStateFlow<RequestsTabState>(RequestsTabState.Loading) }
    val requestsTabState get() = _requestsTabState.asStateFlow()

    private val _refreshing by lazy { MutableStateFlow(false) }
    val refreshing by lazy { _refreshing.asStateFlow() }

    init {
        viewModelScope.launch {
            try {
                _addTabState.emit(loadFriends())
                _requestsTabState.emit(loadRequests())
            } catch (e: Exception) {
                Timber.e(e)
            }

            _search
                .debounce(1.seconds)
                .collect { itSearch ->
                    try {
                        _addTabState.emit(
                            if (itSearch.isNotBlank()) {
                                loadSearch(itSearch)
                            } else {
                                loadFriends()
                            }
                        )
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }
        }
    }

    fun refreshAll() {
        viewModelScope.launch {
            try {
                _refreshing.emit(true)
                _addTabState.emit(
                    _search.value.let { itSearch ->
                        if (itSearch.isNotBlank()) {
                            loadSearch(itSearch)
                        } else {
                            loadFriends()
                        }
                    }
                )
                _requestsTabState.emit(loadRequests())
            } catch (e: Exception) {
                Timber.e(e)
            }
            _refreshing.emit(false)
        }
    }

    private suspend fun loadFriends() =
        AddTabState.Loaded(
            users = friendRepository.getFriends(
                token = keystoreManager.getTokenOrEmpty()
            )
        )

    private suspend fun loadSearch(search: String) =
        AddTabState.Loaded(
            users = friendRepository.searchNew(
                token = keystoreManager.getTokenOrEmpty(),
                search = search
            )
        )

    private suspend fun loadRequests() =
        RequestsTabState.Loaded(
            users = friendRepository.getFriendRequests(
                token = keystoreManager.getTokenOrEmpty()
            )
        )

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
                changeFriendStatus(username, FriendStatus.REQUESTED)
                removeRequest(username)
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
                changeFriendStatus(username, FriendStatus.NOT_FRIEND)
                removeRequest(username)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    private suspend fun changeFriendStatus(username: String, newStatus: FriendStatus) {
        (_addTabState.value as? AddTabState.Loaded)?.let { friends ->
            val usrIdx = friends.users.indexOfFirst { it.username == username }
            val newList = if (usrIdx == -1 && newStatus == FriendStatus.REQUESTED) {
                (_requestsTabState.value as? RequestsTabState.Loaded)?.let { requests ->
                    requests.users.find { it.username == username }
                }?.let {
                    friends.users + it.copy(friendStatus = FriendStatus.FRIEND)
                } ?: friends.users
            } else {
                friends.users.toMutableList().apply {
                    replaceAll {
                        if (it.username == username) {
                            it.copy(friendStatus = newStatus)
                        } else it
                    }
                }
            }
            _addTabState.emit(
                friends.copy(
                    users = newList
                )
            )
        }
    }


    private suspend fun removeRequest(username: String) {
        (_requestsTabState.value as? RequestsTabState.Loaded)?.let { requests ->
            requests.users.find { it.username == username }?.let {
                _requestsTabState.emit(
                    requests.copy(users = requests.users - it)
                )
            }
        }
    }

    sealed interface AddTabState {
        data object Loading : AddTabState
        data class Loaded(val users: List<User>) : AddTabState
    }

    sealed interface RequestsTabState {
        data object Loading : RequestsTabState
        data class Loaded(val users: List<User>) : RequestsTabState
    }
}

