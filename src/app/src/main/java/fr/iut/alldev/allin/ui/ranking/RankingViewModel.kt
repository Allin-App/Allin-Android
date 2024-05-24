package fr.iut.alldev.allin.ui.ranking

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
class RankingViewModel @Inject constructor(
    private val friendRepository: FriendRepository,
    private val keystoreManager: AllInKeystoreManager
) : ViewModel() {
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
        }
    }

    sealed interface State {
        data object Loading : State
        data class Loaded(val friends: List<User>) : State
    }
}