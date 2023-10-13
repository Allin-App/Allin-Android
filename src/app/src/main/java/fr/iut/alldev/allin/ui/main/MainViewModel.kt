package fr.iut.alldev.allin.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.di.AllInCurrentUser
import fr.iut.alldev.allin.data.model.BetStatus
import fr.iut.alldev.allin.data.model.User
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    @AllInCurrentUser val currentUser: User
) : ViewModel() {
    val selectedBet = mutableStateOf<BetStatus?>(null)
}