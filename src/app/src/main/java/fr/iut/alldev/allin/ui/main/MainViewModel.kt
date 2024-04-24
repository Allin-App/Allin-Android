package fr.iut.alldev.allin.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.Participation
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import fr.iut.alldev.allin.data.repository.BetRepository
import fr.iut.alldev.allin.data.repository.UserRepository
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import fr.iut.alldev.allin.ui.core.snackbar.SnackbarType
import fr.iut.alldev.allin.ui.main.event.AllInEvent
import fr.iut.alldev.allin.ui.main.event.DailyReward
import fr.iut.alldev.allin.ui.main.event.ToConfirmBet
import fr.iut.alldev.allin.ui.main.event.WonBet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val betRepository: BetRepository,
    private val keystoreManager: AllInKeystoreManager
) : ViewModel() {

    var loading = mutableStateOf(false)

    val currentUser = userRepository.currentUser.asStateFlow()
    val userCoins = userRepository.userCoins.asStateFlow()
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
                    add(DailyReward(125))

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
                    addAll(betRepository.getWon(token).mapNotNull { result ->
                        currentUser.value?.let {
                            WonBet(
                                user = it,
                                betResult = result
                            )
                        }
                    })
                }.filter { it !in dismissedEvents }
            )
        }
    }

    fun openBetDetail(bet: Bet, onGetBet: (BetDetail) -> Unit) {
        viewModelScope.launch {
            val betDetail = betRepository.getBet(bet.id, keystoreManager.getTokenOrEmpty())
            selectedBet.value = betDetail
            onGetBet(betDetail)
        }
    }

    fun onLogout() {
        viewModelScope.launch {
            keystoreManager.deleteToken()
            userRepository.currentUser.emit(null)
            userRepository.userCoins.emit(null)
        }
    }

    private fun decreaseCoins(amount: Int) {
        viewModelScope.launch {
            userRepository.userCoins.value?.let {
                val newAmount = it - amount
                userRepository.userCoins.emit(newAmount)

            }
        }
    }

    fun participateToBet(stake: Int, response: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loading.value = true
                currentUser.value?.let { user ->
                    decreaseCoins(stake)
                    selectedBet.value?.bet?.let {
                        val participation = Participation(
                            betId = it.id,
                            username = user.username,
                            response = response,
                            stake = stake
                        )
                        betRepository.participateToBet(participation, keystoreManager.getTokenOrEmpty())
                    }
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