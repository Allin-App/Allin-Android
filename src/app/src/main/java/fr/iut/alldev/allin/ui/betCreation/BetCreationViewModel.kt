package fr.iut.alldev.allin.ui.betCreation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.bet.BetFactory
import fr.iut.alldev.allin.data.model.bet.BetType
import fr.iut.alldev.allin.data.repository.BetRepository
import fr.iut.alldev.allin.data.repository.UserRepository
import fr.iut.alldev.allin.ext.FieldErrorState
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class BetCreationViewModel @Inject constructor(
    private val betRepository: BetRepository,
    private val keystoreManager: AllInKeystoreManager,
    private val userRepository: UserRepository
) : ViewModel() {

    private var hasError = mutableStateOf(false)
    var theme = mutableStateOf("")
    var phrase = mutableStateOf("")
    val registerDate = mutableStateOf(ZonedDateTime.now())
    val betDate = mutableStateOf(ZonedDateTime.now())
    var isPublic = mutableStateOf(true)
    var selectedBetType = mutableStateOf(BetType.BINARY)

    val themeError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val phraseError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val registerDateError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val betDateError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)

    private fun initErrorField() {
        themeError.value = FieldErrorState.NoError
        phraseError.value = FieldErrorState.NoError
        registerDateError.value = FieldErrorState.NoError
        betDateError.value = FieldErrorState.NoError
        hasError.value = false
    }

    private fun verifyField(
        registerDateFieldName: String,
        betDateFieldName: String,
    ) {
        if (theme.value.isBlank()) {
            themeError.value = FieldErrorState.Mandatory
            hasError.value = true
        }

        if (phrase.value.isBlank()) {
            phraseError.value = FieldErrorState.Mandatory
            hasError.value = true
        }

        if (registerDate.value <= ZonedDateTime.now()) {
            registerDateError.value =
                FieldErrorState.PastDate(registerDateFieldName.lowercase())
            hasError.value = true
        }

        if (betDate.value <= ZonedDateTime.now()) {
            betDateError.value =
                FieldErrorState.PastDate(betDateFieldName.lowercase())
            hasError.value = true
        } else if (betDate.value < registerDate.value) {
            betDateError.value =
                FieldErrorState.DateOrder(
                    registerDateFieldName.lowercase(),
                    betDateFieldName.lowercase()
                )
            hasError.value = true
        }
    }


    fun createBet(
        themeFieldName: String,
        phraseFieldName: String,
        onError: () -> Unit,
        setLoading: (Boolean) -> Unit,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            setLoading(true)

            initErrorField()
            verifyField(
                themeFieldName,
                phraseFieldName,
            )

            if (!hasError.value) {
                try {
                    userRepository.currentUser.value?.let { currentUser ->
                        val bet = BetFactory.createBet(
                            id = "",
                            betType = selectedBetType.value,
                            theme = theme.value,
                            phrase = phrase.value,
                            endRegisterDate = registerDate.value,
                            endBetDate = betDate.value,
                            isPublic = isPublic.value,
                            nameTeam1 = "",
                            nameTeam2 = "",
                            possibleAnswers = listOf(),
                            creator = currentUser.username
                        )
                        betRepository.createBet(bet, keystoreManager.getTokenOrEmpty())
                        onSuccess()
                    } ?: onError()
                } catch (e: Exception) {
                    Timber.e(e)
                    onError()
                }
            }
            setLoading(false)
        }
    }
}
