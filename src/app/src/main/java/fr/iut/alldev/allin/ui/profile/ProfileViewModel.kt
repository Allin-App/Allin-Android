package fr.iut.alldev.allin.ui.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.data.repository.UserRepository
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import fr.iut.alldev.allin.ui.navigation.Arguments
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
    private val keystoreManager: AllInKeystoreManager
) : ViewModel() {
    private val userId: String? = savedStateHandle[Arguments.USER_ID]

    private val _state by lazy { MutableStateFlow<State>(State.Loading) }
    val state: StateFlow<State> get() = _state.asStateFlow()

    init {
        viewModelScope.launch {

            userRepository.currentUserState.value?.let { currentUser ->
                if (userId == currentUser.id) {
                    userRepository.currentUserState.filterNotNull().collect { user ->
                        _state.emit(State.Loaded(user = user, isCurrentUser = true))
                    }
                }
            }

        }
    }

    fun selectNewProfilePicture(base64: String) {
        viewModelScope.launch {
            try {
                userRepository.setImage(
                    token = keystoreManager.getTokenOrEmpty(),
                    base64 = base64
                )

                userRepository.currentUserState.value?.let {
                    userRepository.updateCurrentUserImage(value = userRepository.getImageUrl(it.id))
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    sealed interface State {
        data object Loading : State
        data class Loaded(val user: User, val isCurrentUser: Boolean) : State
    }
}