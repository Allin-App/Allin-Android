package fr.iut.alldev.allin.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.repository.UserRepository
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val keystoreManager: AllInKeystoreManager
) : ViewModel() {

    var loading = mutableStateOf(false)
    var hasError = mutableStateOf(false)

    val username = mutableStateOf("")
    val password = mutableStateOf("")
    fun onLogin(navigateToDashboard: () -> Unit) {
        viewModelScope.launch {
            loading.value = true

            withContext(Dispatchers.IO) {
                try {
                    userRepository
                        .login(username.value, password.value)
                        ?.let { token -> keystoreManager.putToken(token) }
                    navigateToDashboard()
                } catch (e: Exception) {
                    hasError.value = true

                    if (e !is HttpException || e.code() != 404) {
                        Timber.e(e)
                    }
                }
            }
            loading.value = false
        }
    }

}