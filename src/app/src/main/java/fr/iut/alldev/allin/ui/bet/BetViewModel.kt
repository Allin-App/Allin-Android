package fr.iut.alldev.allin.ui.bet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.repository.BetRepository
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BetViewModel @Inject constructor(
    private val keystoreManager: AllInKeystoreManager,
    private val betRepository: BetRepository
) : ViewModel() {

    private val _isRefreshing by lazy { MutableStateFlow(false) }
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _bets: MutableStateFlow<List<Bet>> by lazy {
        MutableStateFlow(emptyList())
    }

    val bets: StateFlow<List<Bet>> by lazy {
        _bets.asStateFlow()
            .filterNotNull()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000L),
                emptyList()
            )
    }

    init {
        viewModelScope.launch {
            refreshData()
        }
    }

    private suspend fun refreshData() {
        runCatching {
            _bets.emit(betRepository.getAllBets(keystoreManager.getTokenOrEmpty()))
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            refreshData()
            _isRefreshing.emit(false)
        }
    }

}