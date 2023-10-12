package fr.iut.alldev.allin.ui.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.repository.UserRepository
import fr.iut.alldev.allin.ext.ALLOWED_SYMBOLS
import fr.iut.alldev.allin.ext.FieldErrorState
import fr.iut.alldev.allin.ext.containsCharacter
import fr.iut.alldev.allin.ext.isEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val MIN_PASSWORD_SIZE = 8
private const val MIN_USERNAME_SIZE = 3

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var loading = mutableStateOf(false)
    var hasError = mutableStateOf(false)

    val username = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val passwordValidation = mutableStateOf("")

    val usernameError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val emailError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val passwordError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val passwordValidationError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)

    private fun initErrorField(){
        usernameError.value = FieldErrorState.NoError
        emailError.value = FieldErrorState.NoError
        passwordError.value = FieldErrorState.NoError
        passwordValidationError.value = FieldErrorState.NoError
        hasError.value = false
    }
    private fun verifyField(
        usernameFieldName:String,
        emailFieldName:String,
        passwordFieldName:String
    ){
        if(username.value.length < MIN_USERNAME_SIZE){
            usernameError.value = FieldErrorState.TooShort(usernameFieldName,MIN_USERNAME_SIZE)
            hasError.value = true
        }

        if(password.value.length < MIN_PASSWORD_SIZE){
            passwordError.value = FieldErrorState.TooShort(passwordFieldName, MIN_PASSWORD_SIZE)
            hasError.value = true
        }else if(!password.value.containsCharacter(ALLOWED_SYMBOLS)){
            passwordError.value = FieldErrorState.NoSpecialCharacter(passwordFieldName)
            hasError.value = true
        }

        if(!email.value.isEmail()){
            emailError.value = FieldErrorState.BadFormat(emailFieldName, "john@doe.com")
            hasError.value = true
        }

        if(passwordValidation.value != password.value){
            passwordValidationError.value = FieldErrorState.NotIdentical
            hasError.value = true
        }
    }

    fun onRegister(
        usernameFieldName:String,
        emailFieldName: String,
        passwordFieldName:String,
        navigateToDashboard: ()->Unit
    ){
        viewModelScope.launch {
            loading.value = true

            withContext(Dispatchers.IO) {

                initErrorField()
                verifyField(
                    usernameFieldName.lowercase(),
                    emailFieldName.lowercase(),
                    passwordFieldName.lowercase()
                )
                if(!hasError.value) {
                    userRepository.register(
                        username.value,
                        email.value,
                        password.value
                    )
                }
            }
            if(!hasError.value){
                navigateToDashboard()
            }
            loading.value = false
        }
    }
}