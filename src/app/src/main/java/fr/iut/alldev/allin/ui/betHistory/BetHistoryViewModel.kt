package fr.iut.alldev.allin.ui.betHistory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.repository.BetRepository
import fr.iut.alldev.allin.ui.navigation.NavArguments
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BetHistoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val betRepository: BetRepository,
) : ViewModel() {
    private val isCurrent: Boolean? = savedStateHandle[NavArguments.ARG_BET_HISTORY_IS_CURRENT]

    val bets: StateFlow<List<Bet>?> by lazy {
        flowOf(isCurrent).filterNotNull().flatMapConcat {
            if (it) betRepository.getCurrentBets()
            else betRepository.getHistory()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            null
        )
    }
}