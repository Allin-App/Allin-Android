package fr.iut.alldev.allin.ui.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {

    val username = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val passwordValidation = mutableStateOf("")
}