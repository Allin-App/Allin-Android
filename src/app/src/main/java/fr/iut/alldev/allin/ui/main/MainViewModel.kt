package fr.iut.alldev.allin.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.di.AllInCurrentUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserState(val user: User){
    val userCoins = mutableStateOf(user.coins)
}

@HiltViewModel
class MainViewModel @Inject constructor(
    @AllInCurrentUser val currentUser: User
) : ViewModel() {

    var loading = mutableStateOf(false)

    val currentUserState = UserState(currentUser)
    val selectedBet = mutableStateOf<Bet?>(null)

    fun participateToBet(
        stake: Int
    ){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loading.value = true
                currentUserState.userCoins.value += 50
                Thread.sleep(1000)
                loading.value = false
            }
        }
    }

}