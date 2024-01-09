package fr.iut.alldev.allin.ui.welcome


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.repository.UserRepository
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(
    private val keystoreManager: AllInKeystoreManager,
    private val userRepository: UserRepository
) : ViewModel() {

    var loading = mutableStateOf(false)

    fun tryAutoLogin(onSuccess: () -> Unit) {
        viewModelScope.launch {
            loading.value = true
            keystoreManager.createKeystore()
            keystoreManager.getToken()?.let { token ->
                runCatching {
                    userRepository
                        .login(token)
                        ?.let { newToken ->
                            keystoreManager.putToken(newToken)
                        }
                    onSuccess()
                }
            }
            loading.value = false
        }
    }
}