package fr.iut.alldev.allin.ui.betCreation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.api.model.RequestBet
import fr.iut.alldev.allin.data.model.FriendStatus
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.data.model.bet.BetType
import fr.iut.alldev.allin.data.model.bet.NO_VALUE
import fr.iut.alldev.allin.data.model.bet.YES_VALUE
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
    var isPrivate = mutableStateOf(false)
    var selectedBetType = mutableStateOf<BetTypeState>(BetTypeState.Binary)

    val themeError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val phraseError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val registerDateError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val betDateError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)
    val typeError = mutableStateOf<FieldErrorState>(FieldErrorState.NoError)

    private val _friends by lazy { MutableStateFlow<List<User>>(emptyList()) }
    val friends get() = _friends.asStateFlow()

    private val _selectedFriends by lazy { MutableStateFlow<Set<String>>(emptySet()) }
    val selectedFriends get() = _selectedFriends.asStateFlow()

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
            is BetTypeState.Custom -> if (state.answers.size < 2) {
                typeError.value = FieldErrorState.NoResponse
                hasError.value = true
            }

            is BetTypeState.Match -> if (state.team1.isBlank() || state.team2.isBlank()) {
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
                    userRepository.currentUserState.value?.let { _ ->

                        val bet = RequestBet(
                            theme = theme.value,
                            type = selectedBetType.value.type,
                            sentenceBet = phrase.value,
                            endRegistration = registerDate.value,
                            endBet = betDate.value,
                            isPrivate = isPrivate.value,
                            response = selectedBetType.value.getPossibleAnswers(),
                            userInvited = selectedFriends.value.toList()
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

    fun addAnswer(value: String) {
        viewModelScope.launch {
            selectedBetType.value.let {
                if (it is BetTypeState.Custom) {
                    selectedBetType.value = BetTypeState.Custom(answers = it.answers + value)
                }
            }
        }
    }

    fun deleteAnswer(value: String) {
        viewModelScope.launch {
            selectedBetType.value.let {
                if (it is BetTypeState.Custom) {
                    selectedBetType.value = BetTypeState.Custom(answers = it.answers - value)
                }
            }
        }
    }

    fun toggleFriend(value: String) {
        viewModelScope.launch {
            _selectedFriends.value.let { itSelectedFriends ->
                if (itSelectedFriends.contains(value)) {
                    _selectedFriends.emit(itSelectedFriends - value)
                } else {
                    _selectedFriends.emit(itSelectedFriends + value)
                }
            }
        }
    }

    sealed class BetTypeState(val type: BetType) {
        data object Binary : BetTypeState(type = BetType.BINARY) {
            override fun getPossibleAnswers(): List<String> = listOf(YES_VALUE, NO_VALUE)

        }

        data class Match(val team1: String, val team2: String) : BetTypeState(type = BetType.MATCH) {
            override fun getPossibleAnswers(): List<String> = listOf(team1, team2)

        }

        data class Custom(val answers: List<String>) : BetTypeState(type = BetType.CUSTOM) {
            override fun getPossibleAnswers(): List<String> = answers
        }

        abstract fun getPossibleAnswers(): List<String>

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
