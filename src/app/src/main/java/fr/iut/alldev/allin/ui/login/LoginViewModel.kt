package fr.iut.alldev.allin.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.api.interceptors.AllInAPIException
import fr.iut.alldev.allin.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var loading = mutableStateOf(false)
    var hasError = mutableStateOf(false)

    val username = mutableStateOf("")
    val password = mutableStateOf("")
    fun onLogin(
        navigateToDashboard: ()->Unit
    ){
        viewModelScope.launch {
            loading.value = true

            withContext(Dispatchers.IO) {
                try{
                    userRepository.login(username.value, password.value)
                } catch (e: AllInAPIException){
                    hasError.value = true
                }
            }
            if(!hasError.value){
                navigateToDashboard()
            }
            loading.value = false
        }
    }

}