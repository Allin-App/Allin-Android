package fr.iut.alldev.allin.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.repository.UserRepository
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val keystoreManager: AllInKeystoreManager,
    private val userRepository: UserRepository
) : ViewModel() {
    val state: StateFlow<State> by lazy {
        flow {
            delay(1_000L)

            keystoreManager.getToken()?.let { token ->
                runCatching {
                    userRepository
                        .login(token)
                        ?.let { newToken ->
                            keystoreManager.putToken(newToken)
                            Timber.d("Put token $newToken")
                        }
                }.onSuccess {
                    emit(State.Loaded(true))
                }.onFailure {
                    emit(State.Loaded(false))
                }
            } ?: emit(State.Loaded(false))
            
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), State.Loading)
    }

    init {
        keystoreManager.createKeystore()
    }

    sealed interface State {
        data object Loading : State
        data class Loaded(val isLoggedIn: Boolean) : State
    }
}