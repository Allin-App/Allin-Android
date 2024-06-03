package fr.iut.alldev.allin.ui.betCreation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.FriendStatus
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.BetType
import fr.iut.alldev.allin.data.model.bet.BinaryBet
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.repository.BetRepository
import fr.iut.alldev.allin.data.repository.FriendRepository
import fr.iut.alldev.allin.data.repository.UserRepository
import fr.iut.alldev.allin.ext.FieldErrorState
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class BetCreationViewModel @Inject constructor(
    private val betRepository: BetRepository,
    private val keystoreManager: AllInKeystoreManager,
    private val userRepository: UserRepository,
    private val friendRepository: FriendRepository
) : ViewModel() {

    private var hasError = mutableStateOf(false)
    var theme = mutableStateOf("")
    var phrase = mutableStateOf("")
    val registerDate = mutableStateOf(ZonedDateTime.now())
    val betDate = mutableStateOf(ZonedDateTime.now())
    var isPublic = mutableStateOf(true)
    var selectedBetType = mutableStateOf<BetTypeState>(BetTypeState.Binary)

    val themeError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val phraseError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val registerDateError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val betDateError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val typeError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)

    private val _friends by lazy { MutableStateFlow<List<User>>(emptyList()) }
    val friends get() = _friends.asStateFlow()

    init {
        viewModelScope.launch {
            _friends.emit(
                friendRepository.getFriends(
                    token = keystoreManager.getTokenOrEmpty()
                ).filter { it.friendStatus == FriendStatus.FRIEND }
            )
        }
    }

    private fun initErrorField() {
        themeError.value = FieldErrorState.NoError
        phraseError.value = FieldErrorState.NoError
        registerDateError.value = FieldErrorState.NoError
        betDateError.value = FieldErrorState.NoError
        typeError.value = FieldErrorState.NoError
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

        when (val state = selectedBetType.value) {
            BetTypeState.Binary -> Unit
            is BetTypeState.Custom -> if (state.possibleAnswers.size < 2) {
                typeError.value = FieldErrorState.NoResponse
                hasError.value = true
            }

            is BetTypeState.Match -> if (state.team1.isBlank() || state.team2.isBlank()){
                typeError.value = FieldErrorState.Mandatory
                hasError.value = true
            }
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
                    userRepository.currentUserState.value?.let { currentUser ->
                        val bet =

                            when (val type = selectedBetType.value) {
                                BetTypeState.Binary -> {
                                    BinaryBet(
                                        id = "",
                                        theme = theme.value,
                                        creator = currentUser.username,
                                        phrase = phrase.value,
                                        endRegisterDate = registerDate.value,
                                        endBetDate = betDate.value,
                                        isPublic = isPublic.value,
                                        betStatus = BetStatus.WAITING,
                                        totalStakes = 0,
                                        totalParticipants = 0
                                    )
                                }

                                is BetTypeState.Match -> {
                                    MatchBet(
                                        id = "",
                                        theme = theme.value,
                                        creator = currentUser.username,
                                        phrase = phrase.value,
                                        endRegisterDate = registerDate.value,
                                        endBetDate = betDate.value,
                                        isPublic = isPublic.value,
                                        betStatus = BetStatus.WAITING,
                                        totalStakes = 0,
                                        totalParticipants = 0,
                                        nameTeam1 = type.team1,
                                        nameTeam2 = type.team2
                                    )
                                }

                                is BetTypeState.Custom -> {
                                    CustomBet(
                                        id = "",
                                        theme = theme.value,
                                        creator = currentUser.username,
                                        phrase = phrase.value,
                                        endRegisterDate = registerDate.value,
                                        endBetDate = betDate.value,
                                        isPublic = isPublic.value,
                                        betStatus = BetStatus.WAITING,
                                        totalStakes = 0,
                                        totalParticipants = 0,
                                        possibleAnswers = type.possibleAnswers
                                    )
                                }
                            }

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

    fun addAnswer(value: String) {
        viewModelScope.launch {
            selectedBetType.value.let {
                if (it is BetTypeState.Custom) {
                    selectedBetType.value = BetTypeState.Custom(possibleAnswers = it.possibleAnswers + value)
                }
            }
        }
    }

    fun deleteAnswer(value: String) {
        viewModelScope.launch {
            selectedBetType.value.let {
                if (it is BetTypeState.Custom) {
                    selectedBetType.value = BetTypeState.Custom(possibleAnswers = it.possibleAnswers - value)
                }
            }
        }
    }

    sealed class BetTypeState(val type: BetType) {
        data object Binary : BetTypeState(type = BetType.BINARY)
        data class Match(val team1: String, val team2: String) : BetTypeState(type = BetType.MATCH)
        data class Custom(val possibleAnswers: List<String>) : BetTypeState(type = BetType.CUSTOM)

        companion object {
            fun BetType.typeState() =
                when (this) {
                    BetType.BINARY -> Binary
                    BetType.MATCH -> Match(team1 = "", team2 = "")
                    BetType.CUSTOM -> Custom(emptyList())
                }
        }

    }
}
