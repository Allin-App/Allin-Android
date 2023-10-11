package fr.iut.alldev.allin.ui.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    var loading = mutableStateOf(false)

    val username = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val passwordValidation = mutableStateOf("")

    fun onRegister(
        navigateToDashboard: ()->Unit
    ){
        viewModelScope.launch {
            loading.value = true

            withContext(Dispatchers.IO) {
                userRepository.register(
                    username.value,
                    email.value,
                    password.value
                )
            }
            navigateToDashboard()
            loading.value = false
        }
    }
}