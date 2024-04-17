package fr.iut.alldev.allin.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.Participation
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import fr.iut.alldev.allin.data.repository.BetRepository
import fr.iut.alldev.allin.di.AllInCurrentUser
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import fr.iut.alldev.allin.ui.core.snackbar.SnackbarType
import fr.iut.alldev.allin.ui.main.event.AllInEvent
import fr.iut.alldev.allin.ui.main.event.ToConfirmBet
import fr.iut.alldev.allin.ui.main.event.WonBet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserState(val user: User) {
    val userCoins = mutableIntStateOf(user.coins)
}

@HiltViewModel
class MainViewModel @Inject constructor(
    @AllInCurrentUser val currentUser: User,
    private val betRepository: BetRepository,
    private val keystoreManager: AllInKeystoreManager
) : ViewModel() {

    var loading = mutableStateOf(false)

    val currentUserState = UserState(currentUser)
    val selectedBet = mutableStateOf<BetDetail?>(null)
    val dismissedEvents = mutableStateListOf<AllInEvent>()
    val events = mutableStateListOf<AllInEvent>()

    val snackbarContent: MutableState<SnackbarContent?> by lazy { mutableStateOf(null) }

    init {
        fetchEvents()
    }

    fun putSnackbarContent(content: SnackbarContent) {
        snackbarContent.value = content
    }

    fun fetchEvents() {
        viewModelScope.launch {
            val token = keystoreManager.getTokenOrEmpty()
            events.addAll(
                buildList {
                    addAll(betRepository.getToConfirm(token).map { bet ->
                        ToConfirmBet(
                            betDetail = bet,
                            onConfirm = {
                                confirmBet(
                                    response = it,
                                    betId = bet.bet.id
                                )
                            }
                        )
                    })
                    addAll(betRepository.getWon(token).map { result ->
                        WonBet(
                            user = currentUser,
                            betResult = result
                        )
                    })
                }.filter { it !in dismissedEvents }
            )
        }
    }

    fun openBetDetail(bet: Bet) {
        viewModelScope.launch {
            selectedBet.value = betRepository.getBet(bet.id, keystoreManager.getTokenOrEmpty())
        }
    }

    fun deleteToken() {
        viewModelScope.launch {
            keystoreManager.deleteToken()
        }
    }

    fun participateToBet(stake: Int, response: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loading.value = true
                currentUserState.userCoins.intValue -= stake
                selectedBet.value?.bet?.let {
                    val participation = Participation(
                        betId = it.id,
                        username = currentUser.username,
                        response = response,
                        stake = stake
                    )
                    betRepository.participateToBet(participation, keystoreManager.getTokenOrEmpty())
                }
                loading.value = false
            }
        }
    }

    private fun confirmBet(response: String, betId: String) {
        viewModelScope.launch {
            betRepository.confirmBet(
                token = keystoreManager.getTokenOrEmpty(),
                id = betId,
                response = response
            )
        }
    }

    class SnackbarContent(
        val text: String,
        val type: SnackbarType = SnackbarType.STANDARD
    )
}