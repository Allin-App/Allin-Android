package fr.iut.alldev.allin.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : ViewModel() {

    var loading = mutableStateOf(false)
    fun onLogin(
        navigateToDashboard: ()->Unit
    ){
        viewModelScope.launch {
            loading.value = true
            withContext(Dispatchers.IO) {
                Thread.sleep(3000)
            }
            navigateToDashboard()
            loading.value = false
        }
    }

}