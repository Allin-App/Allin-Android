package fr.iut.alldev.allin.ui.bet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.repository.BetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BetViewModel @Inject constructor(
    private val betRepository: BetRepository
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    val bets: StateFlow<List<Bet>> by lazy {
        flow {
            kotlin.runCatching { emitAll(betRepository.getAllBets()) }
        }
            .filterNotNull()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000L),
                emptyList()
            )
    }

    private fun refreshData() {
        Thread.sleep(1000)
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            withContext(Dispatchers.IO) {
                refreshData()
            }
            _isRefreshing.emit(false)
        }
    }

}