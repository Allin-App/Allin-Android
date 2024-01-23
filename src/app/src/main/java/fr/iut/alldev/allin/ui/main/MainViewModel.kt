package fr.iut.alldev.allin.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
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
    val wonBet = mutableStateOf<Bet?>(
        null
        /*        YesNoBet(
                    id = "1",
                    theme = "Theme",
                    phrase = "Phrase",
                    endRegisterDate = ZonedDateTime.now(),
                    endBetDate = ZonedDateTime.now(),
                    isPublic = true,
                    betStatus = BetStatus.Finished(BetFinishedStatus.WON),
                    creator = "creator"
                )*/
    )

    val snackbarContent: MutableState<SnackbarContent?> by lazy { mutableStateOf(null) }
    fun putSnackbarContent(content: SnackbarContent) {
        snackbarContent.value = content
    }

    fun openBetDetail(bet: Bet) {
        viewModelScope.launch {
            selectedBet.value = betRepository.getBet(bet.id, keystoreManager.getToken() ?: "")
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
                    betRepository.participateToBet(participation, keystoreManager.getToken() ?: "")
                }
                loading.value = false
            }
        }
    }

    class SnackbarContent(
        val text: String,
        val type: SnackbarType = SnackbarType.STANDARD
    )
}